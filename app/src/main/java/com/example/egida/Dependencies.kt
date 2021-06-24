package com.example.egida

import com.example.egida.data.DatabaseAuth
import com.example.egida.data.DatabaseDay
import com.example.egida.data.DatabaseUser
import com.example.egida.domain.useCase.UserDbUseCase
import com.example.egida.domain.useCase.day.DayUseCaseImpl
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCaseImpl
import com.example.egida.domain.useCase.userDB.UserDbUseCaseImpl


object Dependencies {

    private val DatabaseAuth: DatabaseAuth by lazy { DatabaseAuth() }
    private val DatabaseUser: DatabaseUser by lazy { DatabaseUser() }
    private val DatabaseDay: DatabaseDay by lazy { DatabaseDay() }

    fun authUseCase(): UserAuthUseCase =
        UserAuthUseCaseImpl(DatabaseAuth)

    fun userDbUseCase(): UserDbUseCase =
        UserDbUseCaseImpl(DatabaseUser)

    fun dayUseCase(): DayUseCase =
        DayUseCaseImpl(DatabaseDay)
}