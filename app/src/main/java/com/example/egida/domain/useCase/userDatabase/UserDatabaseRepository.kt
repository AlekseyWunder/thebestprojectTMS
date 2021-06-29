package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow

interface UserDatabaseRepository {
    fun updateUser(databaseUser: Flow<UserDatabase>)
    fun getUser()
    var databaseUser: Flow<UserDatabase>
    fun addUser(databaseUser: Flow<UserDatabase>): Map<String, Any>
}