package com.example.simpleecommerceapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.Register.ResponseRegister
import com.example.simpleecommerceapp.repository.RepositoryUser

class RegisterViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val repoRegister = RepositoryUser()

    //utl live data search
    var responseRegister = MutableLiveData<ResponseRegister>()

    var errorApi = MutableLiveData<Throwable>()

    var isLoadingProgressBar = MutableLiveData<Boolean>()

    var isEmpty = MutableLiveData<Boolean>()

    var finish = MutableLiveData<Boolean>()

    fun registerUser(username:String, password: String, fullName: String, noHp: String, address: String){
        isLoadingProgressBar.value = true

        //validasi register
        if (username.isNotEmpty()&& password.isNotEmpty()&& fullName.isNotEmpty()&& noHp.isNotEmpty()&& address.isNotEmpty()){
            repoRegister.registerCommand(username,password,fullName,noHp,address,{
                responseRegister.value = it
                isLoadingProgressBar.value = false
                finish.value = true
            },{
                errorApi.value = it
                isLoadingProgressBar.value = false
            })
        }else{
            isLoadingProgressBar.value = false
            isEmpty.value = true
        }

    }
}
