package com.example.egida.presentation.viewModel

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.activity.MainActivity
import com.example.egida.domain.entity.UserAuth
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.utils.replaceActivity
import com.example.egida.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {
    companion object {
        const val TAG = " loginViewModel"
        private const val passwordDont = "Passwords don\'t match"
        private const val passwordAndEmail = "Enter your email and password"
        private const val correctEmail = "Проверьте правильность написания электронного адреса"
    }

    private val userAuthUseCase: UserAuthUseCase by lazy { Dependencies.authUseCase() }

    var id: String = ""
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""
    var fragment = MutableLiveData<Fragment>()
    private var _message = MutableSharedFlow<String>(1)
    var message = _message
    var messageFromDatabaseAuth: SharedFlow<String> = userAuthUseCase.message
        .shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

    fun addUser(fragment: Fragment) {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                val user = UserAuth(email, password)
                userAuthUseCase.addUser(fragment.requireContext(), user)
                addMessageFromDatabaseAuth(fragment)
            } else {
                toast(passwordDont)
            }
        } else {
            toast(passwordAndEmail)
        }
    }

    fun singInUser(fragment: Fragment) {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
        ) {
            val user = UserAuth(email, password)
            userAuthUseCase.singInUser(fragment.requireContext(), user)
            addMessageFromDatabaseAuth(fragment)
            viewModelScope.launch {
                delay(1000)
                if (userAuthUseCase.getCurrentUser() != null) {
                    replaceActivity(fragment.requireView(), MainActivity())
                }
            }
        } else {
            toast(passwordAndEmail)
        }
    }

    fun sendPasswordResetEmail(fragment: Fragment) {
        if (!TextUtils.isEmpty(email)) {
            userAuthUseCase.sendPasswordResetEmail(fragment.requireContext(), email)
//            addMessageFromDatabaseAuth(fragment)
        } else {
            toast(correctEmail)
        }
    }

    private fun toast(string: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _message.emit(string)
            }
        }
    }

    private fun addMessageFromDatabaseAuth(fragment: Fragment) {
        viewModelScope.launch {
            messageFromDatabaseAuth.collect {
                messageCollect(fragment, it)
            }
        }
    }

    suspend fun messageCollect(fragment: Fragment, message: String) {
        withContext(Dispatchers.Default) {
            Handler(Looper.getMainLooper()).post {
                fragment.showToast(message)
            }
        }
    }
}
