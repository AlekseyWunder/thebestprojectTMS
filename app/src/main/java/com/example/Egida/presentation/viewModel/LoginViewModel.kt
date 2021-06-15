package com.example.Egida.presentation.viewModel

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserUseCase
import com.example.Egida.utils.StatesUser
import com.example.Egida.utils.statesUser
import kotlinx.coroutines.launch


class LoginViewModel(
) : ViewModel() {

    private val userUseCase: UserUseCase by lazy { Dependencies.getUserUseCase() }
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""
    var toast = MutableLiveData<String>()
    var fragment = MutableLiveData<Fragment>()

    fun addUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                viewModelScope.launch {
                    val user = User(email, password)
                    userUseCase.addUser(user)
                }
            } else {
                toast.value = statesUser(StatesUser.PasswordsDontMatch)
            }
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun singInUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
        ) {
            val user = User(email, password)
            userUseCase.singInUser(user)
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userUseCase.sendPasswordResetEmail(email)
            toast.value = "Письмо для востановления пороля отправлено на почту"
        } else {
            toast.value = "Проверьте правильность написания электронного адреса"
        }
    }
}






