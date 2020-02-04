package com.example.simpleecommerceapp.ui.productByCategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.Category.ResponseCategory
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.repository.RepositoryProduct

class ProductByCategoryViewModel : ViewModel() {


    // TODO: Implement the ViewModel
    private val repositoryProductByCategory = RepositoryProduct()

    //live data product
    var responseProductByCategory = MutableLiveData<ResponseListProduct>()
    var errorApiProductByCategory = MutableLiveData<Throwable>()
    var isLoadingProductByCategory = MutableLiveData<Boolean>()


    fun showProductByCategory(idCategory: String) {

        isLoadingProductByCategory.value = true
        repositoryProductByCategory.showProductByCategory(idCategory,{
            responseProductByCategory.value = it
            isLoadingProductByCategory.value = false
        },{
            errorApiProductByCategory.value = it
            isLoadingProductByCategory.value = false
        })
    }


}
