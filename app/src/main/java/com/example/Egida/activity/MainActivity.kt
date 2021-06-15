package com.example.Egida.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.Egida.R
import com.example.Egida.databinding.MainActivityBinding
import com.example.Egida.presentation.`object`.AppDrawer
import com.example.Egida.presentation.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainActivityBinding
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFun()

    }

    private fun initFun() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}