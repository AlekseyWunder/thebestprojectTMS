package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.UserDB

interface UserDBRepository {
    fun initUser(user:UserDB)

}