package com.example.simpleecommerceapp.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.ui.cart.CartFragment

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_search, SearchFragment.newInstance())
                .commitNow()
        }
    }
}
