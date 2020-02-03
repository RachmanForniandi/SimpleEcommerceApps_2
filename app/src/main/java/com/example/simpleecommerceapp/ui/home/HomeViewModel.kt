package com.example.simpleecommerceapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.Category.ResponseCategory
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.repository.RepositoryProduct

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repositoryProductPromo = RepositoryProduct()

    //live data product
    var responseProduct = MutableLiveData<ResponseListProduct>()
    var responseProductPromo = MutableLiveData<ResponseProductPromo>()
    var responseProductCategory = MutableLiveData<ResponseCategory>()
    var errorApiProduct = MutableLiveData<Throwable>()
    var isLoadingProduct = MutableLiveData<Boolean>()

    fun getListProduct() {
        isLoadingProduct.value = true
        repositoryProductPromo.showProduct({
            responseProduct.value = it
            isLoadingProduct.value = false
        }){
            errorApiProduct.value = it
            isLoadingProduct.value = false
        }
    }

    fun getProductPromo() {
        isLoadingProduct.value = true
        repositoryProductPromo.showProductPromo({
            responseProductPromo.value = it
            isLoadingProduct.value = false
        }){
            errorApiProduct.value = it
            isLoadingProduct.value = false
        }
    }

    fun getListCategory() {
        isLoadingProduct.value=true
        repositoryProductPromo.getCategory(
            {
                responseProductCategory.value =it
                isLoadingProduct.value = false
            },{
                errorApiProduct.value = it
                isLoadingProduct.value = false
            })
    }


}
