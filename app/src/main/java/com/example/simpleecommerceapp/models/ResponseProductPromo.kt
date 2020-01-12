package com.example.simpleecommerceapp.models

import com.example.simpleecommerceapp.models.DataItem
import com.google.gson.annotations.SerializedName

data class ResponseProductPromo(

    @field:SerializedName("respon")
	val respon: Int? = null,

    @field:SerializedName("data")
	val data: List<DataItem>? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Boolean? = null
)