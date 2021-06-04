package com.example.Egida.domain.useCase

import com.example.Egida.data.Database
import com.example.Egida.domain.entity.User

class UserUSeCaseImpl(
    private val database: Database
) : UserUseCase {

    override fun checkingUser(): Boolean {
        return database.checkingUser()
    }

    override suspend fun addUser(user: User) {
        return database.addUser(user)
    }

    override fun singInUser(user: User) {
        return database.singInUser(user)
    }

    override fun sendPasswordResetEmail(email: String) {
        return database.sendPasswordResetEmail(email)
    }
}