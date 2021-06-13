package com.example.Egida.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Egida.R
import com.example.Egida.presentation.ui.SingInFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SingInFragment.newInstance())
                    .commitNow()
        }
    }
}