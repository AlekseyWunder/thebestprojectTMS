package com.example.egida.data

import android.util.Log
import com.example.egida.R
import com.example.egida.domain.entity.UserAuth
import com.example.egida.domain.useCase.userAUTH.UserAuthRepository
import com.example.egida.utils.AUTH
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class DatabaseAuth() : UserAuthRepository {
    companion object {
        const val TAG = " databaseAUTH"
    }

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private var _message = MutableStateFlow("")
    override var message: StateFlow<String> = _message.asStateFlow()
    private var _messageError = MutableStateFlow("")
    override var messageError: StateFlow<String> = _messageError.asStateFlow()
    private var chek by Delegates.notNull<Boolean>()

    override fun getCurrentUser(): FirebaseUser? {
        return AUTH.currentUser
    }

    override fun addUser(userAuth: UserAuth) {

        AUTH.createUserWithEmailAndPassword(
            userAuth.email,
            userAuth.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                sendEmailVerification()
                scope.launch {
                    _message.emit(R.string.registration_confirmation.toString())
                }
            }
        }.addOnFailureListener {
            if (it is FirebaseAuthException) {
                val errorCode = (it).errorCode
                val errorMessage = authErrors[errorCode]
                if (errorMessage != null) {
                    _message.value = errorMessage.toString()
                } else {
                    if (it is FirebaseNetworkException) {
                        scope.launch {
                            _message.emit("Проверьте интернет")
                        }
                    }
                }
            }
        }
    }


    override fun singInUser(userAuth: UserAuth) {
        AUTH.signInWithEmailAndPassword(
            userAuth.email,
            userAuth.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmailAndPassword:success")
            }
        }.addOnFailureListener {
            errorMessage(it)
        }
    }

    private fun sendEmailVerification() {
        AUTH.currentUser?.sendEmailVerification()
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
        AUTH.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }.addOnFailureListener {
                errorMessage(it)
            }
    }

    override fun singOutUser() {
        AUTH.signOut()
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

    private fun errorMessage(exception: Exception) {
        val errorCode = (exception as FirebaseAuthException).errorCode
        val errorMessage = authErrors[errorCode]
        if (errorMessage != null) {
            scope.launch {
                _message.emit(errorMessage.toString())
            }
        }
    }
}
