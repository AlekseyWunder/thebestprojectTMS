package com.example.Egida.domain.useCase.userDB

import com.example.Egida.domain.entity.UserDB

interface UserDBRepository {
    fun createUser(user: UserDB)
    suspend fun initUser()
    fun updateUser(user: UserDB)
}