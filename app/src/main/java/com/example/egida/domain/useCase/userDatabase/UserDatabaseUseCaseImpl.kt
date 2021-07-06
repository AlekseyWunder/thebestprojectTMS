package com.example.egida.domain.useCase.userDatabase

import com.example.egida.data.DatabaseUser
import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.SharedFlow

class UserDatabaseUseCaseImpl(
    private val data: DatabaseUser
) : UserDatabaseUseCase {

    override suspend fun updateUser(databaseUser: SharedFlow<UserDatabase>) {
        return data.updateUser(databaseUser)
    }

    override fun getUser() {
        return data.getUser()
    }

    override var databaseUser: SharedFlow<UserDatabase>
        get() = data.databaseUser
        set(value) {}

    override fun updateValueUser(databaseUser: SharedFlow<UserDatabase>) {
        return data.updateValueUser(databaseUser)
    }

}