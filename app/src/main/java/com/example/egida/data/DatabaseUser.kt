package com.example.egida.data

import android.util.Log
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseRepository
import com.example.egida.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect

class DatabaseUser : UserDatabaseRepository {
    companion object {
        const val TAG = " databaseUser"
        const val NODE_USERS = "users"
        const val CHILD_ID = "id"
        const val CHILD_LOGIN = "login"
        const val CHILD_FIRST_NAME = "firstName"
        const val CHILD_LAST_NAME = "lastName"
        const val CHILD_CHECK_AGREEMENT_NAME = "checkAgreement"
        const val CHILD_PHONE_NUMBER = "phoneNumber"
        const val CHILD_PHOTO_URL = "photoURL"
        const val CHILD_HEIGHT = "height"
        const val CHILD_WEIGHT = "weight"

    }

    private var _databaseUser = MutableStateFlow(initUser())
    override var databaseUser: Flow<UserDatabase> = _databaseUser.asStateFlow()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var userDatabase: UserDatabase = UserDatabase()
    private val dateMap = mutableMapOf<String, Any>()
    init {
        initFirebase()
        initDatabase()
        UID = AUTH.currentUser?.uid.toString()
    }

    private fun initUser(): UserDatabase {
        return UserDatabase()
    }

    override fun getUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot ->
                scope.launch {
                    _databaseUser.emit(
                        (snapshot.getValue(UserDatabase::class.java) ?: UserDatabase())
                    )
                    Log.d(TAG, " database user loading $snapshot")
                }
            })
    }

    override fun updateUser(databaseUser: Flow<UserDatabase>) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(dateMap)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(DatabaseAuth.TAG, "database update complete")
                }
            }
    }

    override fun addUser(databaseUser: Flow<UserDatabase>): Map<String, Any> {
        val uid = UID
        userDatabase.id = uid
        scope.launch {
            withContext(Dispatchers.IO) {
                databaseUser.collect {
                    userDatabase.id = it.id
                    userDatabase.login = it.login
                    userDatabase.firstName = it.firstName
                    userDatabase.lastName = it.lastName
                    userDatabase.checkAgreement = it.checkAgreement
                    userDatabase.phoneNumber = it.phoneNumber
                    userDatabase.photoURL = it.photoURL
                    userDatabase.height = it.height
                    userDatabase.weight = it.weight
                }
            }
        }
        dateMap[CHILD_ID] = userDatabase.id
        dateMap[CHILD_LOGIN] = userDatabase.login
        dateMap[CHILD_FIRST_NAME] = userDatabase.firstName
        dateMap[CHILD_LAST_NAME] = userDatabase.lastName
        dateMap[CHILD_CHECK_AGREEMENT_NAME] = userDatabase.checkAgreement
        dateMap[CHILD_PHONE_NUMBER] = userDatabase.phoneNumber
        dateMap[CHILD_PHOTO_URL] = userDatabase.photoURL
        dateMap[CHILD_HEIGHT] = userDatabase.height
        dateMap[CHILD_WEIGHT] = userDatabase.weight
        return dateMap
    }
}
