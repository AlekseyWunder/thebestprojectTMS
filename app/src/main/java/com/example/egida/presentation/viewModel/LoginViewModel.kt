package com.example.egida.presentation.viewModel

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserAuth
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {
    companion object {
        const val TAG = " loginViewModel"
        private const val passwordDont = "Passwords don\'t match"
        private const val passwordAndEmail = "Enter your email and password"
        private const val passwordRecovery = "Письмо для востановления пороля отправлено на почт"
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
    var messageDatabase: StateFlow<String> = userAuthUseCase.message
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = "")
    var errorMessage = userAuthUseCase.messageError
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = " ")

    fun addUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                val user = UserAuth(email, password)
                userAuthUseCase.addUser(user)
            } else {
                toast(passwordDont)
            }
        } else {
            toast(passwordAndEmail)
        }
    }

    fun singInUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
        ) {
            val user = UserAuth(email, password)
            userAuthUseCase.singInUser(user)
        } else {
            toast(passwordAndEmail)
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userAuthUseCase.sendPasswordResetEmail(email)
            toast(passwordRecovery)
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
}








