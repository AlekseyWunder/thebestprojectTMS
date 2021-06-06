package com.example.Egida.data

import android.util.Log
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class Database() : UserRepository {
    companion object {
        const val TAG = " database"
    }

    private var chek by Delegates.notNull<Boolean>()
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val cUser: FirebaseUser? = mAuth.currentUser
    override fun checkUser(): Boolean {
        return cUser != null
    }

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.Main) {

            mAuth.createUserWithEmailAndPassword(
                user.email,
                user.password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    this@Database.chek = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    this@Database.chek = false
                }
            }
            delay(2000)
            if (this@Database.chek) {
                sendEmailVerification()
            }
        }
    }

    override fun singInUser(user: User) {
        mAuth.signInWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmailAndPassword:success")
            } else {
                Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
            }
        }
    }

    private fun sendEmailVerification() {
        mAuth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "sendEmailVerification:success")
                } else {
                    Log.d(TAG, "sendEmailVerification:failure", task.exception)
                }
            }
    }

    override fun sendPasswordResetEmail(email: String) {
        var emailAddress = email
        mAuth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                } else {
                    Log.d(TAG, "Email failed")
                }
            }
    }


}