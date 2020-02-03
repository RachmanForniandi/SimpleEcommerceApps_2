package com.example.simpleecommerceapp.ui.productByCategory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.ListProduct.DataItem
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.ui.detailProduct.DetailProductActivity
import com.example.simpleecommerceapp.ui.productByCategory.adapter.ListProductByCategoryAdapter
import com.example.simpleecommerceapp.utils.hide
import com.example.simpleecommerceapp.utils.show
import kotlinx.android.synthetic.main.product_by_category_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class ProductByCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = ProductByCategoryFragment()
    }

    private lateinit var viewModel: ProductByCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.product_by_category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductByCategoryViewModel::class.java)


        val idCategory = activity?.intent?.getStringExtra("id_category")
        viewModel.showProductByCategory(idCategory ?:"")

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseProductByCategory.observe(this,Observer{displayProductByCategory(it)})
        viewModel.isLoadingProductByCategory.observe(this, Observer { showLoadingProductByCategory(it) })
    }

    private fun showLoadingProductByCategory(it: Boolean?) {
        if (it ?: false){
            pbKategori.show()
        }else{
            pbKategori.hide()
        }
    }

    private fun displayProductByCategory(it: ResponseListProduct?) {
        listProduk.adapter = ListProductByCategoryAdapter(it?.data,object :ListProductByCategoryAdapter.onItemClickListener{
            override fun itemClick(item: DataItem?) {
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

}
