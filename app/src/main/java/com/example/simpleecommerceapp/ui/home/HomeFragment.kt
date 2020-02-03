package com.example.simpleecommerceapp.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.Category.ResponseCategory
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.DataItem
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.ui.detailProduct.DetailProductActivity
import com.example.simpleecommerceapp.ui.home.adapter.ListCategoryAdapter
import com.example.simpleecommerceapp.ui.home.adapter.ListProductAdapter
import com.example.simpleecommerceapp.ui.home.adapter.SliderAdapter
import com.example.simpleecommerceapp.ui.productByCategory.ProductByCategoryActivity
import com.example.simpleecommerceapp.utils.hide
import com.example.simpleecommerceapp.utils.show
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getProductPromo()
        viewModel.getListProduct()
        viewModel.getListCategory()


        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseProductPromo.observe(this, Observer { showImageSlider(it) })
        viewModel.responseProduct.observe(this, Observer { showItemProduct(it) })
        viewModel.responseProductCategory.observe(this, Observer { showListCategory(it) })
        viewModel.isLoadingProduct.observe(this, Observer { showLoadingOfProduct(it) })
        viewModel.errorApiProduct.observe(this, Observer {  showError(it)})
    }

    private fun showLoadingOfProduct(it:Boolean?){
        if (it ?: false){
            pbKategori.show()
            pbProduk.show()
        }else{
            pbKategori.hide()
            pbProduk.hide()
        }
    }

    private fun showError(it: Throwable?) {
        Log.d("ERROR",it?.message ?: "")
    }

    private fun showListCategory(it: ResponseCategory?) {
        listKategori.adapter = ListCategoryAdapter(it?.data, object :ListCategoryAdapter.itemClickListener{
            override fun itemClick(item: com.example.simpleecommerceapp.models.Category.DataItem?) {
                startActivity<ProductByCategoryActivity>(
                    "id_category" to item?.id,
                    "category" to item?.kategori
                )
            }

        })
    }


    private fun showItemProduct(it: ResponseListProduct?) {
        listProduct.adapter = ListProductAdapter(it?.data, object : ListProductAdapter.onItemClickListener {
            override fun itemClick(item: com.example.simpleecommerceapp.models.ListProduct.DataItem?) {
                startActivity<DetailProductActivity>(
                    "id" to item?.id,
                    "name" to item?.nama,
                    "description" to item?.deskripsi,
                    "price" to item?.harga,
                    "image" to item?.gambar
                )
            }

        })
    }

    private fun showImageSlider(it: ResponseProductPromo?) {
        imageSlider.sliderAdapter = SliderAdapter(it?.data,object :SliderAdapter.itemClickListener{
            override fun itemClick(dataItem: DataItem?) {
                startActivity<DetailProductActivity>(
                    "id" to dataItem?.id,
                    "name" to dataItem?.nama,
                    "description" to dataItem?.deskripsi,
                    "price" to dataItem?.harga,
                    "image" to dataItem?.gambar
                )
            }

        })
    }

}
