package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserDatabaseUseCase {
    suspend fun updateUser(databaseUser: StateFlow<UserDatabase>)
    fun getUser()
    var databaseUser: StateFlow<UserDatabase>
    suspend fun addUser(databaseUser: StateFlow<UserDatabase>): Map<String, Any>
}
