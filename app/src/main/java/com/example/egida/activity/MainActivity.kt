package com.example.egida.activity

import android.annotation.SuppressLint
import android.app.ActionBar.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.egida.NavigationRVAdapter
import com.example.egida.LoginActivity
import com.example.egida.R
import com.example.egida.databinding.MainActivityBinding
import com.example.egida.domain.entity.Day
import com.example.egida.domain.entity.UserDb
import com.example.egida.presentation.`object`.ClickListener
import com.example.egida.presentation.`object`.NavigationItemModel
import com.example.egida.presentation.`object`.RecyclerTouchListener
import com.example.egida.presentation.ui.*
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.singOutUser
import com.example.egida.utils.userDb
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private lateinit var binding:MainActivityBinding
    private lateinit var mainViewModel: MainViewModel
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.baseline_person_outline_white_24dp, "Home"),
        NavigationItemModel(R.drawable.baseline_chat_white_24dp, "Message"),
        NavigationItemModel(R.drawable.favorite_white_24dp, "Fit"),
        NavigationItemModel(R.drawable.turned_in_not_white_24dp, "Nutrition"),
        NavigationItemModel(R.drawable.grading_white_24dp, "Work"),
        NavigationItemModel(R.drawable.outline_settings_white_24dp, "Settings"),
        NavigationItemModel(R.drawable.outline_people_24, "Friends")
    )
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = findViewById(R.id.drawer_layout)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_content_id, MainFragment.newInstance())
                .commitNow()
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        drawerLayout = findViewById(R.id.drawer_layout)

        // Set the toolbar
        setSupportActionBar(binding.activityMainToolbar)

        // Setup Recyclerview's Layout
        binding.navigationRv.layoutManager = LinearLayoutManager(this)
        binding.navigationRv.setHasFixedSize(true)

        // Add Item Touch Listener
        binding.navigationRv.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        when (position) {
                            0 -> {
                                // # Home Fragment
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Home Fragment")
                                val homeFragment = MainFragment()
                                homeFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, homeFragment).commit()
                            }
                            1 -> {
                                // # Message Fragment
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Message Fragment")
                                val messageFragment = MainFragment()
                                messageFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, messageFragment)
                                    .commit()
                            }
                            2 -> {
                                // # Fit Fragment
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "fit Fragment")
                                val fitFragment = FitFragment()
                                fitFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, fitFragment).commit()
                            }
                            3 -> {
                                // # Nutrition Fragment
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Nutrition Fragment")
                                val NutritionFragment = NutritionFragment()
                                NutritionFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, NutritionFragment)
                                    .commit()
                            }
                            4 -> {
                                // Work
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Work Fragment")
                                val WorkFragment = WorkFragment()
                                WorkFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, WorkFragment).commit()
                            }
                            5 -> {
                                // # Settings Fragment
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Settings Fragment")
                                val settingsFragment = SettingFragment()
                                settingsFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, settingsFragment)
                                    .commit()
                            }
                            6 -> {
                                // # Friends
                                val bundle = Bundle()
                                bundle.putString("fragmentName", "Friends Fragment")
                                val FriendsFragment = MainFragment()
                                FriendsFragment.arguments = bundle
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.activity_main_content_id, FriendsFragment)
                                    .commit()
                            }
                        }

                    }
                })
        )

        // Update Adapter with item data and highlight the default menu item ('Home' Fragment)
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")
        val homeFragment = MainFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()


        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            activity_main_toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Set Header Image
        navigation_header_img.setImageResource(R.drawable._profile_image)

        // Set background of Drawer
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.black))

    }
    init {
        userDb = UserDb()
        DAY = Day()
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
    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Checking for fragment count on back stack
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Go to the previous fragment
                supportFragmentManager.popBackStack()
            } else {
                // Exit the app
                super.onBackPressed()
            }
        }
    }
}


