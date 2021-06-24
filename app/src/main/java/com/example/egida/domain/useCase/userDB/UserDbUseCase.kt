package com.example.egida.domain.useCase

import com.example.egida.domain.entity.UserDb

interface UserDbUseCase {
    fun updateUser(user: UserDb)
    fun getUser()
}