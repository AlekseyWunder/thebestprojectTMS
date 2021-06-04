package com.example.Egida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Egida.presentation.ui.RegistrationFragment
import com.example.Egida.presentation.ui.SingInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SingInFragment.newInstance())
                    .commitNow()
        }
    }
}