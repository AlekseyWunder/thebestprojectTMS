package com.example.Egida.domain.useCase

import com.example.Egida.domain.entity.User

interface UserRepository {
    fun addUser(user:User)
    fun singInUser(user:User)
}