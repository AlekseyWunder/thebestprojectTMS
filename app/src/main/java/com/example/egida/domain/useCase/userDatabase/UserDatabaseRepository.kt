package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.SharedFlow

interface UserDatabaseRepository {
    fun getUser()
    var databaseUser: SharedFlow<UserDatabase>
    suspend fun updateUser()
    fun updateValueUser()
}