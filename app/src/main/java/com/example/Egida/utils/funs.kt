package com.example.Egida.utils

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.Egida.R

fun replaceFragment(view: View, fragment: Fragment) {
    val activity: AppCompatActivity? = (view.context as AppCompatActivity?)
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(R.id.container, fragment)
        ?.addToBackStack(null)
        ?.commit()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(
        this.context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun replaceActivity(view: View, activity: AppCompatActivity){

    val appCompatActivity:AppCompatActivity? = (view.context as AppCompatActivity?)
    val intent = Intent(appCompatActivity,activity::class.java)
    appCompatActivity?.startActivity(intent)
    appCompatActivity?.finish()
}