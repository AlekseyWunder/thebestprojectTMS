package com.example.egida.data


import android.content.Context
import android.util.Log
import com.example.egida.Constants
import com.example.egida.domain.entity.UserAuth
import com.example.egida.domain.useCase.userAUTH.UserAuthRepository
import com.example.egida.utils.AUTH
import com.example.egida.utils.NetworkHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DatabaseAuth() : UserAuthRepository {
    companion object {
        const val TAG = " databaseAUTH"
    }

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private var _message = MutableStateFlow("")
    override var message: Flow<String> = _message.asStateFlow()

    override fun getCurrentUser(): FirebaseUser? {
        return AUTH.currentUser
    }

    override fun addUser(context: Context, userAuth: UserAuth) {
        if (NetworkHelper.isNetworkConnected(context)) {
            AUTH.createUserWithEmailAndPassword(
                userAuth.email,
                userAuth.password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    sendEmailVerification()
                }
            }.addOnFailureListener {
                errorMessage(it)
            }
        } else {
            addMessage(Constants.notNetworkConnect)
        }
    }


    override fun singInUser(context: Context, userAuth: UserAuth) {
        if (NetworkHelper.isNetworkConnected(context)) {
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
        } else {
            addMessage(Constants.notNetworkConnect)
        }
    }

    private fun sendEmailVerification() {
        AUTH.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "sendEmailVerification:success")
                    addMessage(Constants.registrationConfirmation)
                } else {
                    Log.d(TAG, "sendEmailVerification:failure", task.exception)
                }
            }
    }

    override fun sendPasswordResetEmail(context: Context, email: String) {
        if (NetworkHelper.isNetworkConnected(context)) {
            AUTH.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        addMessage(Constants.passwordRecovery)
                        Log.d(TAG, "Email sent.")
                    }
                }.addOnFailureListener {
                    errorMessage(it)
                }
        } else {
            addMessage(Constants.notNetworkConnect)
        }
    }

    override fun singOutUser() {
        AUTH.signOut()
    }

    private val authErrors = mapOf(
        "ERROR_INVALID_CUSTOM_TOKEN" to Constants.errorLoginCustomToken,
        "ERROR_CUSTOM_TOKEN_MISMATCH" to Constants.errorLoginCustomTokenMismatch,
        "ERROR_INVALID_CREDENTIAL" to Constants.errorLoginCredentialMalformedOrExpired,
        "ERROR_INVALID_EMAIL" to Constants.errorLoginInvalidEmail,
        "ERROR_WRONG_PASSWORD" to Constants.errorLoginWrongPassword,
        "ERROR_USER_MISMATCH" to Constants.errorLoginUserMismatch,
        "ERROR_REQUIRES_RECENT_LOGIN" to Constants.errorLoginRequiresRecentLogin,
        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" to Constants.errorLoginAccountsExitsWithDifferentCredential,
        "ERROR_EMAIL_ALREADY_IN_USE" to Constants.errorLoginEmailAlreadyInUse,
        "ERROR_CREDENTIAL_ALREADY_IN_USE" to Constants.errorLoginCredentialAlreadyInUse,
        "ERROR_USER_DISABLED" to Constants.errorLoginUserDisabled,
        "ERROR_USER_TOKEN_EXPIRED" to Constants.errorLoginUserTokenExpired,
        "ERROR_USER_NOT_FOUND" to Constants.errorLoginUserNotFound,
        "ERROR_INVALID_USER_TOKEN" to Constants.errorLoginInvalidUserToken,
        "ERROR_OPERATION_NOT_ALLOWED" to Constants.errorLoginOperationNotAllowed,
        "ERROR_WEAK_PASSWORD" to Constants.errorLoginPasswordIsWeak
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

    private fun addMessage(message: String) {
        scope.launch {
            _message.emit(message)
        }
    }
}
