package com.example.egida.presentation.`object`

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.egida.Constants
import com.example.egida.Dependencies
import com.example.egida.R
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.presentation.ui.FitFragment
import com.example.egida.presentation.ui.NutritionFragment
import com.example.egida.presentation.ui.SettingFragment
import com.example.egida.presentation.ui.WorkFragment
import com.example.egida.utils.downloadAndSetImage
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AppDrawer(
    val activity: AppCompatActivity,
    private val toolbar: Toolbar
) {

    companion object {
        const val drawerItemMessage = 1
        const val drawerItemFit = 2
        const val drawerItemNutrition = 3
        const val drawerItemWork = 4
        const val drawerItemSetting = 5
        const val drawerItemFriends = 6
    }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }
    private lateinit var drawer: Drawer
    lateinit var header: AccountHeader
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var currentProfile: ProfileDrawerItem
    private var userFirstName: String = Constants.firstName
    private var userLastName: String = Constants.lastName
    private var userPhoneNumber: String = Constants.phoneNumber
    private var userUrl: String = Constants.phoneNumber

    fun create() {
        initValue()
        initLoader()
        createHeader()
        createDrawer()
        drawerLayout = drawer.drawerLayout
    }

    fun disableDrawer() {
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.setNavigationOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toolbar.setNavigationOnClickListener {
            drawer.openDrawer()
        }
    }

    private fun createDrawer() {

        drawer = DrawerBuilder()
            .withSliderBackgroundDrawableRes(R.color.black)
            .withActivity(activity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(header)
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
                    when (position) {

                        drawerItemFit -> replaceFragment(FitFragment.newInstance())
                        drawerItemNutrition -> replaceFragment(NutritionFragment.newInstance())
                        drawerItemWork -> replaceFragment(WorkFragment.newInstance())
                        drawerItemSetting -> replaceFragment(SettingFragment.newInstance())
                    }
                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        initValue()
        currentProfile = ProfileDrawerItem()
            .withName("$userFirstName $userLastName")
            .withEmail(userPhoneNumber)
            .withIcon(userUrl)
            .withIdentifier(10)

        header = AccountHeaderBuilder()
            .withActivity(activity)
            .withHeaderBackground(R.drawable.gradient_header)
            .addProfiles(
                currentProfile
            ).build()
    }

    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }

    fun updateHeader() {
        initValue()
        currentProfile
            .withName("$userFirstName $userLastName")
            .withEmail(userPhoneNumber)
            .withIcon(userUrl)
        header.updateProfile(currentProfile)
    }

    private fun initLoader() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
                imageView.downloadAndSetImage(uri.toString())
            }
        })
    }

    private fun initValue() {
        scope.launch {
            userDatabaseUseCase.databaseUser.collect {
                userFirstName = it.firstName
                userLastName = it.lastName
                userPhoneNumber = it.phoneNumber
                userUrl = it.photoURL
            }
        }
    }

}
