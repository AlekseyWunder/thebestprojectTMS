package com.example.egida.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.egida.LoginActivity
import com.example.egida.R
import com.example.egida.databinding.MainActivityBinding
import com.example.egida.domain.entity.Day
import com.example.egida.domain.entity.UserDb
import com.example.egida.presentation.`object`.AppDrawer
import com.example.egida.presentation.ui.MainFragment
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.singOutUser
import com.example.egida.utils.userDb

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainActivityBinding
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    init {
        userDb = UserDb()
        DAY = Day()
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFun()
        mainViewModel.getUser()

    }

    private fun initFun() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater?.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_to_app -> {
                singOutUser()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}


