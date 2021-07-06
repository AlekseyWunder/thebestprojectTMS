package com.example.egida

import com.example.egida.data.DatabaseAuth
import com.example.egida.data.DatabaseDay
import com.example.egida.data.DatabaseUser
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.day.DayUseCaseImpl
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBal
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBalImpl
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCaseImpl
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCaseImpl


object Dependencies {

    private val databaseAuth: DatabaseAuth by lazy { DatabaseAuth() }
    private val databaseUser: DatabaseUser by lazy { DatabaseUser() }
    private val databaseDay: DatabaseDay by lazy { DatabaseDay() }

    fun authUseCase(): UserAuthUseCase =
        UserAuthUseCaseImpl(databaseAuth)

    fun userDatabaseUseCase(): UserDatabaseUseCase =
        UserDatabaseUseCaseImpl(databaseUser)

    fun dayUseCase(): DayUseCase =
        DayUseCaseImpl(databaseDay)

    fun scoreBalUseCase(): UseCaseScoreBal =
        UseCaseScoreBalImpl()
}