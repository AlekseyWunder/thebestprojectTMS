package com.example.Egida.data

import android.util.Log
import android.widget.Toast
import com.example.Egida.App
import com.example.Egida.R
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
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
                }

            }.addOnFailureListener {
                this@Database.chek = false
                val errorCode = (it as FirebaseAuthException).errorCode
                val errorMessage = authErrors[errorCode]
                if (errorMessage != null) {
                    toast(errorMessage)
                }
            }
            delay(2000)
            if (this@Database.chek) {
                sendEmailVerification()
                //При такой архитектуре правиль но ли сдесь сделано
                Toast.makeText(
                    App.instance.applicationContext,
                    "Проверьте вашу почту для подтверждения регистрации",
                    Toast.LENGTH_LONG
                ).show()
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
            }
        }.addOnFailureListener {
            this@Database.chek = false
            val errorCode = (it as FirebaseAuthException).errorCode
            val errorMessage = authErrors[errorCode]
            if (errorMessage != null) {
                toast(errorMessage)
            }
        }
    }

    private suspend fun sendEmailVerification() {
        delay(2000)
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
                }
            }.addOnFailureListener {
                this@Database.chek = false
                val errorCode = (it as FirebaseAuthException).errorCode
                val errorMessage = authErrors[errorCode]
                if (errorMessage != null) {
                    toast(errorMessage)
                }
            }
    }

    private val authErrors = mapOf(
        "ERROR_INVALID_CUSTOM_TOKEN" to R.string.error_login_custom_token,
        "ERROR_CUSTOM_TOKEN_MISMATCH" to R.string.error_login_custom_token_mismatch,
        "ERROR_INVALID_CREDENTIAL" to R.string.error_login_credential_malformed_or_expired,
        "ERROR_INVALID_EMAIL" to R.string.error_login_invalid_email,
        "ERROR_WRONG_PASSWORD" to R.string.error_login_wrong_password,
        "ERROR_USER_MISMATCH" to R.string.error_login_user_mismatch,
        "ERROR_REQUIRES_RECENT_LOGIN" to R.string.error_login_requires_recent_login,
        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" to R.string.error_login_accounts_exits_with_different_credential,
        "ERROR_EMAIL_ALREADY_IN_USE" to R.string.error_login_email_already_in_use,
        "ERROR_CREDENTIAL_ALREADY_IN_USE" to R.string.error_login_credential_already_in_use,
        "ERROR_USER_DISABLED" to R.string.error_login_user_disabled,
        "ERROR_USER_TOKEN_EXPIRED" to R.string.error_login_user_token_expired,
        "ERROR_USER_NOT_FOUND" to R.string.error_login_user_not_found,
        "ERROR_INVALID_USER_TOKEN" to R.string.error_login_invalid_user_token,
        "ERROR_OPERATION_NOT_ALLOWED" to R.string.error_login_operation_not_allowed,
        "ERROR_WEAK_PASSWORD" to R.string.error_login_password_is_weak
    )

    private fun toast(message: Int) {
        Toast.makeText(
            App.instance,
            App.instance.resources.getString(message),
            Toast.LENGTH_LONG
        )
            .show()
    }
}

