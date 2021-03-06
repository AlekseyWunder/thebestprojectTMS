package com.example.egida.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.egida.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun replaceFragment(view: View, fragment: Fragment) {
    val activity: AppCompatActivity? = (view.context as AppCompatActivity?)
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(R.id.container, fragment)
        ?.addToBackStack(null)
        ?.commit()
}

fun Fragment.showToast(message: String) {
    if (message.isNotEmpty()) {
        Toast.makeText(
            this.context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun replaceActivity(view: View, activity: Activity) {
    val intent = Intent(view.context, activity::class.java)
    view.context?.startActivity(intent)

}

fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.background_main)
        .into(this)
}