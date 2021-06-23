package com.example.egida.domain.useCase.userDB

import com.example.egida.domain.entity.UserDb

interface UserDbRepository {
    fun updateUser(user: UserDb)
    fun getUser()
}