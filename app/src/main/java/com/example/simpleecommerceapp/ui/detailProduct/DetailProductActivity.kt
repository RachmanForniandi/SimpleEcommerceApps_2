package com.example.simpleecommerceapp.ui.detailProduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleecommerceapp.R

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_product_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailProductFragment.newInstance())
                .commitNow()
        }
    }

}
