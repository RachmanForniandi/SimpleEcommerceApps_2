package com.example.simpleecommerceapp.repository

import com.example.simpleecommerceapp.models.Login.ResponseLogin
import com.example.simpleecommerceapp.models.Register.ResponseRegister
import com.example.simpleecommerceapp.network.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryUser {

    private val api = NetworkConfig.useService()
    private val composite = CompositeDisposable()

    //for login
    fun loginCommand(
        username: String,
        password: String,
        responseHandler:(ResponseLogin)->Unit,
        errorHandler:(Throwable)->Unit
    ) {
        composite.add(
            api.getLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
        )
    }

    fun registerCommand(
        username: String,
        password: String,
        fullName:String,
        noHp:String,
        alamat: String,
        responseHandler:(ResponseRegister)->Unit,
        errorHandler:(Throwable)->Unit
    ) {
        composite.add(
            api.getRegister(username, password,fullName,noHp,alamat)
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
        composite.clear()
    }


}