package com.example.simpleecommerceapp.models.AddOrDeleteItemCart

import com.google.gson.annotations.SerializedName

data class ResponseAddItemToCart (
    @field:SerializedName("respon")
    val respon: Int? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

