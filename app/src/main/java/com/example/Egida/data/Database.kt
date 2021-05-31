package com.example.Egida.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserRepository
import com.google.firebase.auth.FirebaseAuth

class Database() : UserRepository {
    companion object {
        const val TAG = " database"
    }


    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    @SuppressLint("ShowToast")
    override fun addUser(user: User) {
        mAuth.createUserWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")

            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
            }
        }
    }

    override fun singInUser(user: User) {
        mAuth.signInWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {

            } else {

            }
        }

    }
}