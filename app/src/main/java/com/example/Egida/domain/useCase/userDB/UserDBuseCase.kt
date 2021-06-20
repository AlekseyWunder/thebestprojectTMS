package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.UserDB

interface UserDBUseCase {
    fun createUser(user: UserDB)
    suspend fun initUser()
    fun updateUser(user: UserDB)
}