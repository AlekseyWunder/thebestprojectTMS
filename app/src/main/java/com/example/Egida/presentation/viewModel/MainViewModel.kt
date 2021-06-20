package com.example.Egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Egida.Dependencies
import com.example.Egida.domain.useCase.UserAUTHUseCase


class MainViewModel : ViewModel() {
    private val userAUTHUseCase: UserAUTHUseCase by lazy { Dependencies.userAUTHUseCase() }
    var toast = MutableLiveData<String>()


    fun checkUser(): Boolean {
       var cUser =  userAUTHUseCase.getCurrentUser()
        return if (cUser != null) {
            if (!cUser.isEmailVerified) {
                toast.value = "Проверьте вашу почту для подтверждения емэйл адресса"
            }
            true
        } else {
            false
        }
    }

    fun singOutUser(){
        userAUTHUseCase.singOutUser()
    }
}