package com.example.Egida.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.Egida.LoginActivity
import com.example.Egida.R
import com.example.Egida.databinding.MainActivityBinding
import com.example.Egida.presentation.`object`.AppDrawer
import com.example.Egida.presentation.ui.MainFragment
import com.example.Egida.utils.singOutUser

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainActivityBinding
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

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
    override fun onCreateOptionsMenu(menu: Menu,):Boolean {
        menuInflater?.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_to_app -> {
                singOutUser()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}


