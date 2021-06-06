package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun checkUser(): Boolean
    suspend fun addUser(user:User)
    fun singInUser(user:User)
    fun sendPasswordResetEmail(email: String)
}