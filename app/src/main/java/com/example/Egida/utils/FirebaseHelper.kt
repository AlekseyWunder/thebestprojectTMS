package com.example.Egida.utils

import com.example.Egida.domain.entity.UserDB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var USER: FirebaseUser
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER_DB: UserDB
lateinit var UID:String

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


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    USER_DB = UserDB()
   // UID = AUTH.currentUser!!.uid

}

fun initDatabase() {
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun singOutUser() {
    AUTH.signOut()
}