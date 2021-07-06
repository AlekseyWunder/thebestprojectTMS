package com.example.egida.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.LoginActivity
import com.example.egida.R
import com.example.egida.appActivity
import com.example.egida.databinding.MainActivityBinding
import com.example.egida.presentation.`object`.AppDrawer
import com.example.egida.presentation.ui.MainFragment
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.singOutUser
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), DrawerController {

    private lateinit var mBinding: MainActivityBinding
    private lateinit var mToolbar: Toolbar
    private lateinit var drawer: AppDrawer
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

    override fun onStart() {
        super.onStart()
        appActivity = this
        initFields()
        initFun()
        mainViewModel.updateUserAndDay()

    }

    private fun initFun() {
        setSupportActionBar(mToolbar)
        drawer.create()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        drawer = AppDrawer(this, mToolbar)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            lifecycleScope.launch {
                withContext(Dispatchers.Default){
                    mainViewModel.addProfileImage(uri)
                }
                mainViewModel.setAddPhoto()
            }


        }
    }

    override fun openDrawer() {
        drawer.enableDrawer()
    }

    override fun closeDrawer() {
        drawer.disableDrawer()
    }
}



