package com.example.egida

import com.example.egida.data.DataStorage
import com.example.egida.data.cloudSource.DatabaseAuth
import com.example.egida.data.cloudSource.DatabaseDay
import com.example.egida.data.cloudSource.DatabaseUser
import com.example.egida.data.localSource.LocalCalculationScorbal
import com.example.egida.data.localSource.LocalSourceDay
import com.example.egida.data.localSource.LocalSourceUser
import com.example.egida.domain.useCase.dataStorage.DataStorageUsecase
import com.example.egida.domain.useCase.dataStorage.DataStorageUsecaseImpl
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.day.DayUseCaseImpl
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBal
import com.example.egida.domain.useCase.scoreBall.UseCaseScoreBalImpl
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCaseImpl
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCaseImpl


object Dependencies {
    private val localSourceDay: LocalSourceDay by lazy { LocalSourceDay() }
    private val localSourceUser: LocalSourceUser by lazy { LocalSourceUser() }
    private val localCalculationScorbal: LocalCalculationScorbal by lazy {
        LocalCalculationScorbal(
            localSourceUser
        )
    }
    private val databaseAuth: DatabaseAuth by lazy { DatabaseAuth() }
    private val databaseUser: DatabaseUser by lazy { DatabaseUser(localSourceUser) }
    private val databaseDay: DatabaseDay by lazy { DatabaseDay(localSourceDay) }
    private val dataStorage: DataStorage by lazy { DataStorage() }

    fun authUseCase(): UserAuthUseCase =
        UserAuthUseCaseImpl(databaseAuth)

    fun userDatabaseUseCase(): UserDatabaseUseCase =
        UserDatabaseUseCaseImpl(databaseUser)

    fun dayUseCase(): DayUseCase =
        DayUseCaseImpl(databaseDay)

    fun scoreBalUseCase(): UseCaseScoreBal =
        UseCaseScoreBalImpl(localCalculationScorbal)

    fun dataStorageUsecase(): DataStorageUsecase =
        DataStorageUsecaseImpl(dataStorage)
}