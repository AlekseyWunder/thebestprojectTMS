package com.example.Egida.data

import android.util.Log
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.userDB.UserDBRepository
import com.example.Egida.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DatabaseUser : UserDBRepository {
    companion object {
        const val TAG = " databaseUser"
    }

    init {
        initFirebase()
        initDatabase()
        UID = AUTH.currentUser?.uid.toString()

    }

    override fun createUser(user: UserDB) {
        addUser(user)
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(addUser(user))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(DatabaseAUTH.TAG, "database complete")
                }
            }
    }

    override suspend fun initUser() {
        withContext(Dispatchers.IO) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER_DB = it.getValue(UserDB::class.java) ?: UserDB()
            })
        delay(2000)}
    }

    override fun updateUser(user: UserDB) {
        addUser(user)
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(addUser(user))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(DatabaseAUTH.TAG, "database update complete")
                }
            }
    }

    private fun addUser(user: UserDB): Map<String, Any> {
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