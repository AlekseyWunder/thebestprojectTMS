package com.example.Egida.domain.useCase.userDB

import com.example.Egida.domain.entity.UserDB

interface UserDBRepository {
    fun createUser(user: UserDB)
    fun updateUser(user: UserDB)
    fun getUser()
}