package com.example.egida.domain.useCase.userAUTH

import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser

interface UserAuthUseCase {

    suspend fun addUser(userAuth: UserAuth)
    fun singInUser(userAuth: UserAuth)
    fun sendPasswordResetEmail(email: String)
    fun getCurrentUser():FirebaseUser?
    fun singOutUser()
}