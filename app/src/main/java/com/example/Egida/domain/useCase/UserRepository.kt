package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun checkingUser(): Boolean
    suspend fun addUser(user:User)
    fun singInUser(user:User)
    fun sendPasswordResetEmail(email: String)
}