package com.example.Egida.domain.useCase.userDB

import com.example.Egida.data.DatabaseUser
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.UserDBUseCase

class UserDBUseCaseImpl(
    private val databaseUser: DatabaseUser
): UserDBUseCase {
    override fun createUser(user: UserDB) {
       return databaseUser.createUser(user)
    }

    override fun updateUser(user: UserDB) {
        return databaseUser.updateUser(user)
    }

    override fun getUser() {
        return databaseUser.getUser()
    }
}