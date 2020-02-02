package com.example.simpleecommerceapp.repository

import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.network.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryProductPromo {
    private val apiProduct = NetworkConfig.useService()
    private val compositeProduct = CompositeDisposable()

    //get product promo
    fun showProductPromo(
        responseHandler:(ResponseProductPromo)->Unit,
        errorHandler: (Throwable)->Unit){
        compositeProduct.add(
            apiProduct.getProductPromo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }

    //get search product
    fun searchForProduct(
        product:String,
        responseHandler:(ResponseListProduct)->Unit,
        errorHandler: (Throwable)->Unit){
        compositeProduct.add(
            apiProduct.search(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }


    fun onClear(){
        compositeProduct.clear()
    }

}