package com.example.simpleecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.simpleecommerceapp.ui.home.HomeFragment
import com.example.simpleecommerceapp.ui.home.fragments.CartFragment
import com.example.simpleecommerceapp.ui.home.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }*/
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = HomeFragment()
        addFragment(fragment)
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.menu_home -> {
                    val fragment = HomeFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_cart -> {
                    val fragment = CartFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    val fragment = ProfileFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }


}