package com.example.simpleecommerceapp.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.AddOrDeleteItemCart.ResponseAddItemToCart
import com.example.simpleecommerceapp.models.Cart.ResponseListItemCart
import com.example.simpleecommerceapp.models.Category.ResponseCategory
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.repository.RepositoryProduct

class CartViewModel : ViewModel() {
    private val repositorycart = RepositoryProduct()

    //live data cart
    var responseItemCart = MutableLiveData<ResponseListItemCart>()
    var responseDeleteItemCart = MutableLiveData<ResponseAddItemToCart>()
    var errorApiCart = MutableLiveData<Throwable>()
    var isLoadingCart = MutableLiveData<Boolean>()


    fun showItemCart(){
        isLoadingCart.value =true
        repositorycart.getListItemCart({
            responseItemCart.value = it
            isLoadingCart.value = false
        },{
            errorApiCart.value = it
            isLoadingCart.value = false
        })
    }

    fun deleteItem(id:String){
        isLoadingCart.value =true
        repositorycart.deleteItemOnCart(id,
            {
                responseDeleteItemCart.value = it
                isLoadingCart.value = false
            },{
                errorApiCart.value = it
                isLoadingCart.value = false
            })
    }
}
