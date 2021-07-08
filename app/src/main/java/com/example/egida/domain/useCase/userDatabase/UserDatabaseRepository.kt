package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.User
import kotlinx.coroutines.flow.SharedFlow

interface UserDatabaseRepository {
    fun getUser()
    var databaseUser: SharedFlow<User>
    suspend fun updateUser()
    fun updateValueUser()
    fun addPhotoUrl(photoUrl: String)
}