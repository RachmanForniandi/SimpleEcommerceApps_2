package com.example.simpleecommerceapp.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.AddOrDeleteItemCart.ResponseAddItemToCart
import com.example.simpleecommerceapp.models.Cart.DataItem
import com.example.simpleecommerceapp.models.Cart.ResponseListItemCart
import com.example.simpleecommerceapp.ui.cart.adapter.CartAdapter
import com.example.simpleecommerceapp.ui.cart.adapter.onItemCartClickListener
import com.example.simpleecommerceapp.ui.detailProduct.DetailProductActivity
import com.example.simpleecommerceapp.utils.hide
import com.example.simpleecommerceapp.utils.show
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.showItemCart()

        attachObserveCart()
    }

    private fun attachObserveCart() {

        viewModel.responseItemCart.observe(this, Observer {showResponseCart(it) })
        viewModel.isLoadingCart.observe(this, Observer { showLoadingCart(it) })
        viewModel.responseDeleteItemCart.observe(this, Observer { showResponseDelItem(it) })
    }

    private fun showResponseDelItem(it: ResponseAddItemToCart?) {
        if (it?.status ?: false){
            alert("Success to delete", "Message"){
                yesButton {  }
            }.show()
        } else {
            alert("Failed to delete", "Message"){
                yesButton {  }
            }.show()
        }
    }

    private fun showLoadingCart(it:Boolean?){
        if (it ?: false){
            pbKeranjang.show()
        } else {
            pbKeranjang.hide()
        }
    }

    private fun showResponseCart(it: ResponseListItemCart?) {
        listKeranjang.adapter = CartAdapter(it?.data,object : onItemCartClickListener{
            override fun itemClick(item: DataItem?) {
                startActivity<DetailProductActivity>(
                    "id" to item?.id,
                    "name" to item?.nama,
                    "price" to item?.harga,
                    "image" to item?.gambar
                )
            }

            override fun delItem(item: DataItem?) {
                viewModel.deleteItem(item?.id ?:"")
            }

        })
    }

}
