package com.example.simpleecommerceapp.network

import com.example.simpleecommerceapp.models.AddOrDeleteItemCart.ResponseAddItemToCart
import com.example.simpleecommerceapp.models.Cart.ResponseListItemCart
import com.example.simpleecommerceapp.models.ListProduct.ResponseListProduct
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.models.Login.ResponseLogin
import com.example.simpleecommerceapp.models.Register.ResponseRegister
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

    @FormUrlEncoded
    @POST("register")
    fun getRegister(@Field("username")username:String,
                    @Field("password")password:String,
                    @Field("fullname") fullname: String,
                    @Field("nohp") nohp: String,
                    @Field("alamat") alamat: String):Single<ResponseRegister>


    @FormUrlEncoded
    @POST("search")
    fun search(@Field("produk") produk: String): Single<ResponseListProduct>

    @FormUrlEncoded
    @POST("getProdukByKategori")
    fun getProductByCategory
                (@Field("id_kategori") id_kategori: String): Observable<ResponseListProduct>

    @GET("getProdukPromo")
    fun getProductPromo():Observable<ResponseProductPromo>

    @GET("getProduk")
    fun getProduct(): Observable<ResponseListProduct>

    @GET("getKeranjang")
    fun getItemCart(): Observable<ResponseListItemCart>

    @FormUrlEncoded
    @POST("deleteKeranjang")
    fun deleteItemCart(@Field("id") id: String): Single<ResponseAddItemToCart>

    @FormUrlEncoded
    @POST("addKeranjang")
    fun addItemToCart(
        @Field("iduser") id_user: String,
        @Field("idproduk") id_produk: String
    ): Single<ResponseAddItemToCart>


}