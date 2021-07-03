package com.example.egida.domain.useCase.userAUTH

import com.example.egida.data.DatabaseAuth
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

class UserAuthUseCaseImpl(
    private val databaseAUTH: DatabaseAuth
) : UserAuthUseCase {

    override fun addUser(userAuth: UserAuth) {
        return databaseAUTH.addUser(userAuth)
    }

    override fun singInUser(userAuth: UserAuth) {
        return databaseAUTH.singInUser(userAuth)
    }

    override fun sendPasswordResetEmail(email: String) {
        return databaseAUTH.sendPasswordResetEmail(email)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return databaseAUTH.getCurrentUser()
    }

    override fun singOutUser() {
        return databaseAUTH.singOutUser()
    }

    override var message: StateFlow<String>
        get() = databaseAUTH.message
        set(value) {}
    override var messageError: StateFlow<String>
        get() = databaseAUTH.messageError
        set(value) {}
}