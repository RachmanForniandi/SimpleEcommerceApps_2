package com.example.simpleecommerceapp.ui.detailProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.AddOrDeleteItemCart.ResponseAddItemToCart
import com.example.simpleecommerceapp.utils.Constants
import com.example.simpleecommerceapp.utils.SessionManager
import com.example.simpleecommerceapp.utils.formatToRupiah
import kotlinx.android.synthetic.main.detail_product_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

class DetailProductFragment : Fragment() {

    companion object {
        fun newInstance() = DetailProductFragment()
    }

    private lateinit var viewModel: DetailProductViewModel

    private var session: SessionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailProductViewModel::class.java)
        // TODO: Use the ViewModel

        session = SessionManager(activity!!)

        tvNama.text = activity?.intent?.getStringExtra("name")
        tvDeskripsi.text = activity?.intent?.getStringExtra("description")
        tvHarga.text = formatToRupiah(activity?.intent?.getStringExtra("price") ?: "")

        val idProduct = activity?.intent?.getStringExtra("id")

        Glide.with(ivProduk)
            .load(Constants.IMAGE_URL+activity?.intent?.getStringExtra("image"))
            .into(ivProduk)

        btnAddCart.onClick {
            viewModel.addToCart(session?.idUser ?:"",idProduct ?:"")
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.responseAddToCart.observe(this, Observer { showResponse(it) })
    }

    private fun showResponse(it: ResponseAddItemToCart?) {
        if (it?.status ?: false){
            alert ("${it?.status ?:false} success add to cart","Confirmation" ){
                yesButton {
                    activity?.finish()
                }
            }.show()
        }
    }

}
