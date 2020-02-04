package com.example.simpleecommerceapp.ui.detailProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.AddOrDeleteItemCart.ResponseAddItemToCart
import com.example.simpleecommerceapp.repository.RepositoryProduct

class DetailProductViewModel : ViewModel() {

    private val repository = RepositoryProduct()

    var responseAddToCart = MutableLiveData<ResponseAddItemToCart>()

    var errorApiCart = MutableLiveData<Throwable>()

    fun addToCart(idUser:String, idProduct:String){
        repository.addItemOnCart(idUser,idProduct,{
            responseAddToCart.value = it
        },{
            errorApiCart.value = it
        })

    }
}
