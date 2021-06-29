package com.example.egida.domain.useCase.userDatabase

import com.example.egida.data.DatabaseUser
import com.example.egida.domain.entity.UserDatabase
import kotlinx.coroutines.flow.Flow

class UserDatabaseUseCaseImpl(
    private val data: DatabaseUser
) : UserDatabaseUseCase {

    override fun updateUser(databaseUser: Flow<UserDatabase>) {
        return data.updateUser(databaseUser)
    }

    override fun getUser() {
        return data.getUser()
    }

    override var databaseUser: Flow<UserDatabase>
        get() = data.databaseUser
        set(value) {}

    override fun addUser(databaseUser: Flow<UserDatabase>): Map<String, Any> {
        return data.addUser(databaseUser)
    }
}