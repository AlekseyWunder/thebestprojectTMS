package com.example.egida.domain.useCase.userDatabase

import com.example.egida.data.cloudSource.DatabaseUser
import com.example.egida.domain.entity.User
import kotlinx.coroutines.flow.SharedFlow

class UserDatabaseUseCaseImpl(
    private val data: DatabaseUser
) : UserDatabaseUseCase {

    override suspend fun updateUser() {
        return data.updateUser()
    }

    override fun getUser() {
        return data.getUser()
    }

    override var databaseUser: SharedFlow<User> = data.databaseUser

    override fun updateValueUser() {
        return data.updateValueUser()
    }

    override fun addPhotoUrl(photoUrl: String) {
        return data.addPhotoUrl(photoUrl)
    }

}