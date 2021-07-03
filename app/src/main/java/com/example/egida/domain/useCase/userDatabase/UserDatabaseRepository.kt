package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UserDatabaseRepository {
    fun getUser()
    var databaseUser: SharedFlow<UserDatabase>
    suspend fun addUser(databaseUser: SharedFlow<UserDatabase>): Map<String, Any>
    suspend fun updateUser(databaseUser: SharedFlow<UserDatabase>)
}