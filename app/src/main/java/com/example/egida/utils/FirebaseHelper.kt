package com.example.egida.utils

import com.example.egida.domain.entity.Day
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var USER: FirebaseUser
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var DAY:Day
lateinit var UID:String


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
}

fun initDatabase() {
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun singOutUser() {
    AUTH.signOut()
}