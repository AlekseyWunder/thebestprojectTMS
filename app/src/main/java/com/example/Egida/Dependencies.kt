package com.example.Egida

import com.example.Egida.data.Database
import com.example.Egida.domain.useCase.UserUSeCaseImpl
import com.example.Egida.domain.useCase.UserUseCase


object Dependencies {

    private val database: Database by lazy { Database() }

    fun getUserUseCase(): UserUseCase =
        UserUSeCaseImpl(database)
}