package com.example.Egida.domain.useCase.userAUTH

import com.example.Egida.data.DatabaseAUTH
import com.example.Egida.domain.entity.UserAUTH
import com.google.firebase.auth.FirebaseUser

class UserAUTHUSeCaseImpl(
    private val databaseAUTH: DatabaseAUTH
) : UserAUTHUseCase {

    override suspend fun addUser(userAUTH: UserAUTH) {
        return databaseAUTH.addUser(userAUTH)
    }

    override fun singInUser(userAUTH: UserAUTH) {
        return databaseAUTH.singInUser(userAUTH)
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