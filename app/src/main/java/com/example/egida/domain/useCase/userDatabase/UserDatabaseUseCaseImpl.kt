package com.example.egida.domain.useCase.userDatabase

import com.example.egida.data.DatabaseUser
import com.example.egida.domain.entity.UserDatabase
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

    override var databaseUser: SharedFlow<UserDatabase>
        get() = data.databaseUser
        set(value) {
            databaseUser = value
        }

    override fun updateValueUser() {
        return data.updateValueUser()
    }

}