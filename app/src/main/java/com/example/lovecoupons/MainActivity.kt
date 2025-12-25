package com.example.lovecoupons

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.couponsFragment,
                R.id.datesFragment,
                R.id.funFragment
            )
        )
        bottomNavigationView.setupWithNavController(navController)

        // Dark mode toggle
        val darkModeToggle = findViewById<ImageButton>(R.id.darkModeToggle)
        darkModeToggle.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        // Add slide animations for fragment transitions
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment
            // Animations are handled by NavController
        }
    }
}