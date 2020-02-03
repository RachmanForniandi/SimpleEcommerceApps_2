package com.example.simpleecommerceapp.models.Cart


import com.google.gson.annotations.SerializedName

data class ResponseListItemCart(

	@field:SerializedName("respon")
	val respon: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)