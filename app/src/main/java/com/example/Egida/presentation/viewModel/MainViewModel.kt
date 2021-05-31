package com.example.Egida.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserUseCase


class MainViewModel(

) : ViewModel() {

    private val userUseCase: UserUseCase by lazy { Dependencies.getUserUseCase() }
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""

    private var coincidence = MutableLiveData<Boolean>()
    fun getCoincidence(): MutableLiveData<Boolean> = coincidence

    fun checkingPasswords(){
        if (password==doublePassword){
            addUser()
        } else{
            coincidence.value = false
        }
    }

    fun addUser() {
        val user = User(email, password)
        userUseCase.addUser(user)
    }

    fun singInUser() {
        val user = User(email, password)
        userUseCase.singInUser(user)
    }
}