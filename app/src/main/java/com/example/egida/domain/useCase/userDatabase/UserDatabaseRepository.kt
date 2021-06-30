package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.StateFlow

interface UserDatabaseRepository {
    suspend fun updateUser(databaseUser: StateFlow<UserDatabase>)
    fun getUser()
    var databaseUser: StateFlow<UserDatabase>
    suspend fun addUser(databaseUser: StateFlow<UserDatabase>): Map<String, Any>
}