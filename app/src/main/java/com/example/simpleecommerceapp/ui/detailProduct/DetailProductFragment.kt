package com.example.simpleecommerceapp.ui.detailProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.utils.Constants
import com.example.simpleecommerceapp.utils.SessionManager
import com.example.simpleecommerceapp.utils.formatToRupiah
import kotlinx.android.synthetic.main.detail_product_fragment.*

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
            .load(Constants.IMAGE_URL+activity?.intent?.getStringExtra("gambar"))
            .into(ivProduk)

    }

}
