package com.example.egida.domain.useCase.userAUTH

import android.content.Context
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {

    fun addUser(context: Context, userAuth: UserAuth)
    fun singInUser(context: Context, userAuth: UserAuth)
    fun singOutUser()
    fun getCurrentUser(): FirebaseUser?
    var message: Flow<String>
    fun sendPasswordResetEmail(context: Context, email: String)

}
