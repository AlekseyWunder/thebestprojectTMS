package com.example.egida.domain.useCase.userDatabase

import com.example.egida.data.DatabaseUser
import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class UserDatabaseUseCaseImpl(
    private val data: DatabaseUser
) : UserDatabaseUseCase {

    override suspend fun updateUser(databaseUser: StateFlow<UserDatabase>) {
        return data.updateUser(databaseUser)
    }

    override fun getUser() {
        return data.getUser()
    }

    override var databaseUser: StateFlow<UserDatabase>
        get() = data.databaseUser
        set(value) {}

    override suspend fun addUser(databaseUser: StateFlow<UserDatabase>): Map<String, Any> {
        return data.addUser(databaseUser)
    }
}