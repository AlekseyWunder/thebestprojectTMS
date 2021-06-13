package com.example.Egida.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.Egida.R
import com.example.Egida.databinding.MainActivityBinding
import com.example.Egida.presentation.ui.MainFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainActivityBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mToolbar: Toolbar


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
        createHeader()
        createDrawer()
    }

    private fun createDrawer() {

        mDrawer = DrawerBuilder()
            .withSliderBackgroundDrawableRes(R.drawable.gradient)
            .withActivity(this)
            .withToolbar(mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(1)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_message))
                    .withSelectable(false)
                    .withIcon(R.drawable.message_white_24dp),
                PrimaryDrawerItem().withIdentifier(2)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_fit))
                    .withSelectable(false)
                    .withIcon(R.drawable.favorite_white_24dp),
                PrimaryDrawerItem().withIdentifier(3)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_nutrition))
                    .withSelectable(false)
                    .withIcon(R.drawable.turned_in_not_white_24dp),
                PrimaryDrawerItem().withIdentifier(4)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_work))
                    .withSelectable(false)
                    .withIcon(R.drawable.grading_white_24dp),
                PrimaryDrawerItem().withIdentifier(5)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_setting))
                    .withSelectable(false)
                    .withIcon(R.drawable.outline_settings_white_24dp),
                PrimaryDrawerItem().withIdentifier(6)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.drawer_text_friends))
                    .withSelectable(false)
                    .withIcon(R.drawable.outline_people_24)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    Toast.makeText(applicationContext,position.toString(),Toast.LENGTH_SHORT).show()
                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.gradient_header)
            .addProfiles(
                ProfileDrawerItem().withIcon(R.drawable.baseline_people_outline_black_24dp)
                    .withName("Антон Горбатович")
            ).build()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
    }
}