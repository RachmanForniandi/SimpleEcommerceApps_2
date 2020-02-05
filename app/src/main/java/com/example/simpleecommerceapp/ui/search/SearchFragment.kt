package com.example.simpleecommerceapp.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        acSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.lookForItem(s.toString())
            }
        })
        attachSearchObserver()
    }

    private fun attachSearchObserver() {
        viewModel.responseSearch.observe(this, Observer { showResponseSearch(it) })
        viewModel.errorApi.observe(this, Observer { showErrorSearch(it) })
        viewModel.isLoadingProgressBar.observe(this, Observer { showLoadingSearch(it) })
    }

    private fun showLoadingSearch(it: Boolean?) {
        if (it ?: false)pbSearch.show()else pbSearch.hide()
    }

    private fun showErrorSearch(it: Throwable?) {
        alert (it?.message.toString(),"Error"){
            yesButton {  }
        } .show()
    }

    private fun showResponseSearch(it: ResponseListProduct?) {
        searchListProduct.adapter = ListProductByCategoryAdapter(it?.data,object :ListProductByCategoryAdapter.onItemClickListener{
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
