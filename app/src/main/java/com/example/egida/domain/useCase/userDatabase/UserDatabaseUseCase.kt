package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UserDatabaseUseCase {
    suspend fun updateUser()
    fun getUser()
    var databaseUser: SharedFlow<UserDatabase>
    fun updateValueUser()
    fun addPhotoUrl(photoUrl: String)
}
