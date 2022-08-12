package com.example.btvn2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.btvn2.R
import com.example.btvn2.fragments.AccountFragment
import com.example.btvn2.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())

        bottom_nav.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment
            when (item.itemId) {
                R.id.item_home -> {
                    selectedFragment = HomeFragment()
                    loadFragment(selectedFragment)
                    true
                }
                R.id.item_account -> {
                    selectedFragment = AccountFragment()
                    loadFragment(selectedFragment)
                    true
                }
                else -> {
                    false
                }
            }

        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}