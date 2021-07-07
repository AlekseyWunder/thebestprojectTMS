package com.example.egida.data.cloudSource

import android.util.Log
import com.example.egida.data.localSource.LocalSourceUser
import com.example.egida.domain.entity.User
import com.example.egida.domain.useCase.localsource.LocalSourceUserRepository
import com.example.egida.domain.useCase.userDatabase.UserDatabaseRepository
import com.example.egida.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

class DatabaseUser(
    localSourceUser: LocalSourceUser
) : UserDatabaseRepository, LocalSourceUserRepository {
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

    private var _databaseUser = MutableSharedFlow<User>(replay = 1)
    override var databaseUser: SharedFlow<User> = _databaseUser
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var user: User = User()
    private val dateMap = mutableMapOf<String, Any>()

    init {
        initFirebase()
        initDatabase()
        CURRENT_UID = AUTH.currentUser?.uid.toString()
    }

    override var localUser: User = localSourceUser.localUser


    override fun getUser() {

        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot ->
                scope.launch {
                    _databaseUser.emit(
                        (snapshot.getValue(User::class.java) ?: User())
                    )
                    Log.d(TAG, " database user loading $snapshot")
                }
            })
    }

    override suspend fun updateUser() {
        scope.launch {
            delay(1000)
            addUser()
            Log.d(TAG, "updaterUser start $dateMap")
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).updateChildren(dateMap)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "database update complete ")
                    }
                }
        }
    }

    private fun addUser(): Map<String, Any> {
        val uid = CURRENT_UID
        user.id = uid
        updateValueUser()
        dateMap[CHILD_ID] = localUser.id
        dateMap[CHILD_FIRST_NAME] = localUser.firstName
        dateMap[CHILD_LAST_NAME] = localUser.lastName
        dateMap[CHILD_CHECK_AGREEMENT_NAME] = localUser.checkAgreement
        dateMap[CHILD_PHONE_NUMBER] = localUser.phoneNumber
        dateMap[CHILD_PHOTO_URL] = localUser.photoURL
        dateMap[CHILD_HEIGHT] = localUser.height
        dateMap[CHILD_WEIGHT] = localUser.weight
        Log.d(TAG, "addUser complete")
        return dateMap
    }

    override fun updateValueUser() {
        scope.launch {
            withContext(Dispatchers.Default) {
                databaseUser.collect {
                    localUser.id = it.id
                    localUser.firstName = it.firstName
                    localUser.lastName = it.lastName
                    localUser.checkAgreement = it.checkAgreement
                    localUser.phoneNumber = it.phoneNumber
                    localUser.photoURL = it.photoURL
                    localUser.height = it.height
                    localUser.weight = it.weight
                    Log.d(TAG, "finish updateValueUser $databaseUser")
                }
            }
        }
    }

    override fun addPhotoUrl(photoUrl: String) {
        scope.launch {
            databaseUser.collect {
                it.photoURL = photoUrl
            }
        }
    }

}
