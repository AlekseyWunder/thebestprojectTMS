package com.example.egida.data

import android.util.Log
import com.example.egida.domain.entity.UserDb
import com.example.egida.domain.useCase.userDB.UserDbRepository
import com.example.egida.utils.*

class DatabaseUser : UserDbRepository {
    companion object {
        const val TAG = " databaseUser"
    }

    init {
        initFirebase()
        initDatabase()
        UID = AUTH.currentUser?.uid.toString()
        userDb = UserDb()
    }

    override fun getUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                userDb = it.getValue(UserDb::class.java) ?: UserDb()
                Log.d(TAG, " database user loading")
                Log.d(TAG, " ${userDb.height}")
                Log.d(TAG, " ${userDb.firstName}")
                Log.d(TAG, " ${userDb.lastName}")
                Log.d(TAG, " ${userDb.id}")
                Log.d(TAG, " ${userDb.phoneNumber}")
            })
    }

    override fun updateUser(user: UserDb) {
        addUser(user)
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(addUser(user))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(DatabaseAuth.TAG, "database update complete")
                }
            }
    }

    private fun addUser(user: UserDb): Map<String, Any> {
        val uid = UID
        user.id = uid
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_ID] = user.id
        dateMap[CHILD_LOGIN] = user.login
        dateMap[CHILD_FIRST_NAME] = user.firstName
        dateMap[CHILD_LAST_NAME] = user.lastName
        dateMap[CHILD_CHECK_AGREEMENT_NAME] = user.checkAgreement
        dateMap[CHILD_PHONE_NUMBER] = user.phoneNumber
        dateMap[CHILD_PHOTO_URL] = user.photoURL
        dateMap[CHILD_HEIGHT] = user.height
        dateMap[CHILD_WEIGHT] = user.weight
        return dateMap
    }

}