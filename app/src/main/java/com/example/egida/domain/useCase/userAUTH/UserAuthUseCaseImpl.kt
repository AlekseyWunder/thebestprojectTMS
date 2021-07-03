package com.example.egida.domain.useCase.userAUTH

import android.content.Context
import com.example.egida.data.DatabaseAuth
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class UserAuthUseCaseImpl(
    private val databaseAUTH: DatabaseAuth
) : UserAuthUseCase {

    override fun addUser(context: Context, userAuth: UserAuth) {
        return databaseAUTH.addUser(context, userAuth)
    }

    override fun singInUser(context: Context, userAuth: UserAuth) {
        return databaseAUTH.singInUser(context, userAuth)
    }

    override fun sendPasswordResetEmail(context: Context, email: String) {
        return databaseAUTH.sendPasswordResetEmail(context, email)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return databaseAUTH.getCurrentUser()
    }

    override fun singOutUser() {
        return databaseAUTH.singOutUser()
    }

    override var message: Flow<String>
        get() = databaseAUTH.message
        set(value) {}
}
