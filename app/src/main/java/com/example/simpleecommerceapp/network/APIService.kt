package com.example.simpleecommerceapp.network

import com.example.simpleecommerceapp.models.ResponseProductPromo
import com.example.simpleecommerceapp.models.login.ResponseLogin
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @FormUrlEncoded
    @POST("login")
    fun getLogin(@Field("username")username:String,
                 @Field("password")password:String):Single<ResponseLogin>

    @GET("getProdukPromo")
    fun getProductPromo():Observable<ResponseProductPromo>
}