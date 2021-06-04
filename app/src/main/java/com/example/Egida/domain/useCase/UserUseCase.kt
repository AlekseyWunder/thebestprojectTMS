package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.User

interface UserUseCase {
    fun checkingUser():Boolean
    suspend fun addUser(user: User)
    fun singInUser(user: User)
    fun sendPasswordResetEmail(email: String)
}