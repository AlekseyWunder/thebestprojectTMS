package com.example.egida.domain.useCase.userAUTH

import com.example.egida.data.DatabaseAuth
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser

class UserAuthUseCaseImpl(
    private val databaseAUTH: DatabaseAuth
) : UserAuthUseCase {

    override suspend fun addUser(userAuth: UserAuth) {
        return databaseAUTH.addUser(userAuth)
    }

    override fun singInUser(userAuth: UserAuth) {
        return databaseAUTH.singInUser(userAuth)
    }

    override fun sendPasswordResetEmail(email: String) {
        return databaseAUTH.sendPasswordResetEmail(email)
    }

    override fun getCurrentUser():FirebaseUser? {
        return databaseAUTH.getCurrentUser()
    }

    override fun singOutUser() {
        return databaseAUTH.singOutUser()
    }
}