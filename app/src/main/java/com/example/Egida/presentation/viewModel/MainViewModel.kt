package com.example.Egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Egida.Dependencies
import com.example.Egida.domain.useCase.UserUseCase
import com.example.Egida.utils.USER

class MainViewModel : ViewModel() {
    private val userUseCase: UserUseCase by lazy { Dependencies.getUserUseCase() }
    var toast = MutableLiveData<String>()

    fun checkUser(): Boolean {
        return if (USER != null) {
            if (!USER!!.isEmailVerified) {
                toast.value = "Проверьте вашу почту для подтверждения емэйл адресса"
            }
            true
        } else {
            false
        }
    }
}