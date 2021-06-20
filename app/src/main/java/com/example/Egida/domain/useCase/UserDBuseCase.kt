package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.UserDB

interface UserDBUseCase {
    fun initUser(user: UserDB)
}