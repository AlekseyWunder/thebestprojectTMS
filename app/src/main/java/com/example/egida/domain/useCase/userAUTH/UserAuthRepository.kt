package com.example.egida.domain.useCase.userAUTH

import com.example.egida.domain.entity.Day
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserAuthRepository {
    fun addUser(userAuth: UserAuth)
    fun singInUser(userAuth: UserAuth)
    fun sendPasswordResetEmail(email: String)
    fun singOutUser()
    fun getCurrentUser(): FirebaseUser?
    var message: StateFlow<String>
    var messageError: StateFlow<String>
}