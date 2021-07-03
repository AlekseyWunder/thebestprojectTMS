package com.example.egida.domain.useCase.userAUTH

import android.content.Context
import com.example.egida.domain.entity.UserAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserAuthUseCase {

    fun addUser(context: Context, userAuth: UserAuth)
    fun singInUser(context: Context, userAuth: UserAuth)
    fun sendPasswordResetEmail(context: Context, email: String)
    fun getCurrentUser(): FirebaseUser?
    fun singOutUser()
    var message: Flow<String>

}
