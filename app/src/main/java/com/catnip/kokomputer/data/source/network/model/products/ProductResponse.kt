package com.catnip.kokomputer.data.source.network.model.products

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<ProductItemResponse>?,
)