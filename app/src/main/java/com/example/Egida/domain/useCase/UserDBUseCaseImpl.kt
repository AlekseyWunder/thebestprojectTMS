package com.example.Egida.domain.useCase

import com.example.Egida.data.DatabaseUser
import com.example.Egida.domain.entity.UserDB

class UserDBUseCaseImpl(
    private val databaseUser: DatabaseUser
):UserDBUseCase {
    override fun initUser(user: UserDB) {
       return databaseUser.initUser(user)
    }
}