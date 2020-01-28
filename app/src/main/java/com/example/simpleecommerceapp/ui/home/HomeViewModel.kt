package com.example.simpleecommerceapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.repository.RepositoryProductPromo

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repositoryProductPromo = RepositoryProductPromo()

    //live data product
    var responseProductPromo = MutableLiveData<ResponseProductPromo>()
    var errorApiProductPromo = MutableLiveData<Throwable>()
    var isLoadingProductPromo = MutableLiveData<Boolean>()

    fun getProductPromo() {
        isLoadingProductPromo.value = true
        repositoryProductPromo.showProductPromo({
            responseProductPromo.value = it
            isLoadingProductPromo.value = false
        }){
            errorApiProductPromo.value = it
            isLoadingProductPromo.value = false
        }
    }
}
