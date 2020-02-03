package com.example.simpleecommerceapp.models.Category


import com.google.gson.annotations.SerializedName

data class ResponseCategory(

	@field:SerializedName("respon")
	val respon: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)