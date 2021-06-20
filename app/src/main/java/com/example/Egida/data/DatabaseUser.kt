package com.example.Egida.data

import android.util.Log
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.UserDBRepository
import com.example.Egida.utils.*

class DatabaseUser : UserDBRepository {
    companion object {
        const val TAG = " databaseUser"
    }

    init {
        initFirebase()
        initDatabase()
    }

    override fun initUser(user: UserDB) {
        val uid = USER.uid
        user.id = uid
        val dateMap = mutableMapOf<String,Any>()
        dateMap[CHILD_ID] = user.id
        dateMap[CHILD_LOGIN] = user.login
        REF_DATABASE_ROOT.child(NODE_USERS).child(user.login).updateChildren(dateMap).addOnCompleteListener { task->
            if(task.isSuccessful){
                Log.d(DatabaseAUTH.TAG, "database complite")
            }
        }
    }


}