package com.example.Egida.domain.useCase.userAUTH

import com.example.Egida.domain.entity.UserAUTH
import com.google.firebase.auth.FirebaseUser

interface UserAUTHRepository {
    suspend fun addUser(userAUTH:UserAUTH)
    fun singInUser(userAUTH:UserAUTH)
    fun sendPasswordResetEmail(email: String)
    fun singOutUser()
    fun getCurrentUser():FirebaseUser?
}