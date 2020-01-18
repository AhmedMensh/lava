package com.android.pharous.app.lava.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.pharous.app.lava.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = findViewById<View>(R.id.nav_host_fragment)
        var navController = findNavController(navHostFragment)
        bottomNavMenu.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->

            if ( navDestination.id ==  R.id.measurementFragment || navDestination.id ==  R.id.homeFragment
                || navDestination.id == R.id.scheduleFragment || navDestination.id == R.id.measurementFragment
                || navDestination.id == R.id.settingsFragment
                || navDestination.id == R.id.performanceFragment) {

                bottomNavMenu.visibility = View.VISIBLE


            } else {
                bottomNavMenu.visibility = View.GONE

        }



    }
    }



}
