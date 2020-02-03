package com.example.simpleecommerceapp.ui.productByCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleecommerceapp.R
import kotlinx.android.synthetic.main.product_by_category_activity.*

class ProductByCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_by_category_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductByCategoryFragment.newInstance())
                .commitNow()
        }
        titleBar.text = "Kategori "+intent?.getStringExtra("kategori")
    }

}
