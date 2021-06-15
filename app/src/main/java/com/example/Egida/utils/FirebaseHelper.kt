package com.example.Egida.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

var AUTH: FirebaseAuth = FirebaseAuth.getInstance()
var USER: FirebaseUser? = AUTH.currentUser

const val NODE_USERS = "users"