package com.example.Egida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Egida.databinding.LoginActivityBinding
import com.example.Egida.presentation.ui.SingInFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SingInFragment.newInstance())
                    .commitNow()
        }
    }
}