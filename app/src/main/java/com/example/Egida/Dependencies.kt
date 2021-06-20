package com.example.Egida

import com.example.Egida.data.DatabaseAUTH
import com.example.Egida.data.DatabaseUser
import com.example.Egida.domain.useCase.UserAUTHUSeCaseImpl
import com.example.Egida.domain.useCase.UserAUTHUseCase
import com.example.Egida.domain.useCase.UserDBUseCase
import com.example.Egida.domain.useCase.UserDBUseCaseImpl


object Dependencies {

    private val DATABASE_AUTH: DatabaseAUTH by lazy { DatabaseAUTH() }
    private val DATABASE_USER: DatabaseUser by lazy { DatabaseUser() }

    fun userAUTHUseCase(): UserAUTHUseCase =
        UserAUTHUSeCaseImpl(DATABASE_AUTH)

    fun userDBUseCase(): UserDBUseCase =
        UserDBUseCaseImpl(DATABASE_USER)
}