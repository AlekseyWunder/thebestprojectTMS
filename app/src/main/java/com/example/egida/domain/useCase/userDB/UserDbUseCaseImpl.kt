package com.example.egida.domain.useCase.userDB

import com.example.egida.data.DatabaseUser
import com.example.egida.domain.entity.UserDb
import com.example.egida.domain.useCase.UserDbUseCase

class UserDbUseCaseImpl(
    private val databaseUser: DatabaseUser
) : UserDbUseCase {

    override fun updateUser(user: UserDb) {
        return databaseUser.updateUser(user)
    }

    override fun getUser() {
        return databaseUser.getUser()
    }
}