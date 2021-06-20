package com.example.Egida.presentation.`object`

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.Egida.R
import com.example.Egida.presentation.ui.SettingFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawer(
    val activity: AppCompatActivity,
    private val toolbar: Toolbar
) {
    private lateinit var mDrawer: Drawer
    lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout:DrawerLayout

    fun create() {
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.setNavigationOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }

    private fun createDrawer() {

        mDrawer = DrawerBuilder()
            .withSliderBackgroundDrawableRes(R.drawable.gradient)
            .withActivity(activity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(1)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_message)
                    .withSelectable(false)
                    .withIcon(R.drawable.message_white_24dp),
                PrimaryDrawerItem().withIdentifier(2)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_fit)
                    .withSelectable(false)
                    .withIcon(R.drawable.favorite_white_24dp),
                PrimaryDrawerItem().withIdentifier(3)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_nutrition)
                    .withSelectable(false)
                    .withIcon(R.drawable.turned_in_not_white_24dp),
                PrimaryDrawerItem().withIdentifier(4)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_work)
                    .withSelectable(false)
                    .withIcon(R.drawable.grading_white_24dp),
                PrimaryDrawerItem().withIdentifier(5)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_setting)
                    .withSelectable(false)
                    .withIcon(R.drawable.outline_settings_white_24dp),
                PrimaryDrawerItem().withIdentifier(6)
                    .withIconTintingEnabled(true)
                    .withName(R.string.drawer_text_friends)
                    .withSelectable(false)
                    .withIcon(R.drawable.outline_people_24)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when(position){
                        5 -> activity.supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.container, SettingFragment.newInstance())
                            .commit()
                    }

                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(activity)
            .withHeaderBackground(R.drawable.gradient_header)
            .addProfiles(
                ProfileDrawerItem().withIcon(R.drawable.baseline_people_outline_black_24dp)
                    .withName("Антон Горбатович")
            ).build()
    }

}