package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.UserDB

interface UserDBUseCase {
    fun createUser(user: UserDB)
    fun updateUser(user: UserDB)
    fun getUser()
}