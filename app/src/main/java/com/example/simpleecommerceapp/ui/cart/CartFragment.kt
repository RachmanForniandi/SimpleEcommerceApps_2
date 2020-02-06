package com.example.simpleecommerceapp.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.Cart.ResponseListItemCart
import kotlinx.android.synthetic.main.fragment_cart.*

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
    }

    private fun showResponseCart(it: ResponseListItemCart?) {
        listKeranjang.adapter
    }

}
