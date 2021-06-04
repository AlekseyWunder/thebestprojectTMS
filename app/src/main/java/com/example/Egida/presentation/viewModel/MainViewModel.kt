package com.example.Egida.presentation.viewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserUseCase
import kotlinx.coroutines.launch


class MainViewModel(

) : ViewModel() {

    private val userUseCase: UserUseCase by lazy { Dependencies.getUserUseCase() }
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""
    private val user = User(email, password)
    var toast = MutableLiveData<String>()

    fun checkingUser(): Boolean {
        return userUseCase.checkingUser()
    }

    fun addUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                viewModelScope.launch {
                    userUseCase.addUser(user)
                    toast.value = "Проверьте вашу почту для подтверждения регистрации"
                }

            } else {
                toast.value = "Пароли не совпадают"
            }
        } else {
            toast.value = "Введите логин и пароль"
        }
    }

    fun singInUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
        ) {
            userUseCase.singInUser(user)
            toast.value = "Пользователь добавлен"
        } else {
            toast.value = "Введите логин и пароль"
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userUseCase.sendPasswordResetEmail(email)
            toast.value = "Письмо для востановления пороля отправлено на почту"
        } else{
            toast.value = "Проверьте правильность написания электронного адреса"
        }
    }

}