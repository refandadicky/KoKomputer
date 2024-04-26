package com.catnip.kokomputer.data.source.network.model.products

import com.google.gson.annotations.SerializedName

data class ProductItemResponse (
    @SerializedName("id")
    val id : String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("img_url")
    val img_url : String?,
    @SerializedName("price")
    val price : Double?,
    @SerializedName("desc")
    val desc : String?,
    @SerializedName("rating")
    val rating : Double?,
)