package com.example.egida.domain.useCase.userDatabase

import com.example.egida.domain.entity.User
import kotlinx.coroutines.flow.SharedFlow

interface UserDatabaseUseCase {
    suspend fun updateUser()
    fun getUser()
    var databaseUser: SharedFlow<User>
    fun updateValueUser()
    fun addPhotoUrl(photoUrl: String)
}
