package com.example.Egida.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var USER: FirebaseUser
lateinit var REF_DATABASE_ROOT: DatabaseReference

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_LOGIN = "login"
const val CHILD_FIRST_NAME = "first_name"
const val CHILD_LAST_NAME = "last_name"
const val CHILD_PHONE_NUMBER = "phone_number"
const val CHILD_PHOTO = "photo"
const val CHILD_HEIGHT = "height"
const val CHILD_WEIGHT = "weight"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    USER = AUTH.currentUser!!
}

fun initDatabase() {
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun singOutUser() {
    AUTH.signOut()
}