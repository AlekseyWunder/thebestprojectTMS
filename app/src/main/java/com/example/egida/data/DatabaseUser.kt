package com.example.egida.data

import android.util.Log
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseRepository
import com.example.egida.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect

class DatabaseUser : UserDatabaseRepository {
    companion object {
        const val TAG = " databaseUser"
        const val NODE_USERS = "users"
        const val CHILD_ID = "id"
        const val CHILD_FIRST_NAME = "firstName"
        const val CHILD_LAST_NAME = "lastName"
        const val CHILD_CHECK_AGREEMENT_NAME = "checkAgreement"
        const val CHILD_PHONE_NUMBER = "phoneNumber"
        const val CHILD_PHOTO_URL = "photoURL"
        const val CHILD_HEIGHT = "height"
        const val CHILD_WEIGHT = "weight"
    }

    private var _databaseUser = MutableSharedFlow<UserDatabase>(replay = 1)
    override var databaseUser: SharedFlow<UserDatabase> = _databaseUser.asSharedFlow()
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

    override suspend fun updateUser(databaseUser: SharedFlow<UserDatabase>) {
        Log.d(TAG, "updaterUser start $databaseUser")
        Log.d(TAG, "updaterUser start $dateMap")
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(addUser(databaseUser))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "database update complete ")
                }
            }
    }

    override suspend fun addUser(databaseUser: SharedFlow<UserDatabase>): Map<String, Any> {

        val uid = UID
        userDatabase.id = uid
        scope.launch {
            async {
                databaseUser.collect {
                    userDatabase.id = it.id
                    userDatabase.firstName = it.firstName
                    userDatabase.lastName = it.lastName
                    userDatabase.checkAgreement = it.checkAgreement
                    userDatabase.phoneNumber = it.phoneNumber
                    userDatabase.photoURL = it.photoURL
                    userDatabase.height = it.height
                    userDatabase.weight = it.weight
                }
            }.await()
        }
        dateMap[CHILD_ID] = userDatabase.id
        dateMap[CHILD_FIRST_NAME] = userDatabase.firstName
        dateMap[CHILD_LAST_NAME] = userDatabase.lastName
        dateMap[CHILD_CHECK_AGREEMENT_NAME] = userDatabase.checkAgreement
        dateMap[CHILD_PHONE_NUMBER] = userDatabase.phoneNumber
        dateMap[CHILD_PHOTO_URL] = userDatabase.photoURL
        dateMap[CHILD_HEIGHT] = userDatabase.height
        dateMap[CHILD_WEIGHT] = userDatabase.weight
        Log.d(TAG, "addUser complete")
        return dateMap
    }

}
