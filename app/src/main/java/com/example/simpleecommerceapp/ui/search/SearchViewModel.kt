package com.example.simpleecommerceapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.repository.RepositoryProduct

class SearchViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repoSearch = RepositoryProduct()

    //utl live data search
    var responseSearch = MutableLiveData<ResponseListProduct>()

    var errorApi = MutableLiveData<Throwable>()

    var isLoadingProgressBar = MutableLiveData<Boolean>()

    fun lookForItem(product:String){
        isLoadingProgressBar.value = true
        repoSearch.searchForProduct(product,{
            responseSearch.value = it
            isLoadingProgressBar.value =false
        },{
            errorApi.value = it
            isLoadingProgressBar.value =false
        })
    }
}
