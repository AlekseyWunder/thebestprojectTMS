package com.example.Egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.UserDBUseCase
import com.example.Egida.domain.useCase.userAUTH.UserAUTHUseCase


class MainViewModel : ViewModel() {

    companion object {
        const val TAG = " mainViewModel"
    }

    private val userAUTHUseCase: UserAUTHUseCase by lazy { Dependencies.userAUTHUseCase() }
    private val userDBUseCase: UserDBUseCase by lazy { Dependencies.userDBUseCase() }

    var toast = MutableLiveData<String>()
    var userDB = UserDB()


    fun checkUser(): Boolean {
        var cUser = userAUTHUseCase.getCurrentUser()
        return if (cUser != null) {
            if (!cUser.isEmailVerified) {
                toast.value = "Проверьте вашу почту для подтверждения емэйл адресса"
            }
            true
        } else {
            false
        }
    }

    fun singOutUser() {
        userAUTHUseCase.singOutUser()
    }

    fun getUser(){
        userDBUseCase.getUser()
    }
}