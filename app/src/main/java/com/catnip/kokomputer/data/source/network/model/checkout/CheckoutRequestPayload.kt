package com.catnip.kokomputer.data.source.network.model.checkout

import com.google.gson.annotations.SerializedName

data class CheckoutRequestPayload (
    @SerializedName("orders")
    val orders: List<CheckoutItemPayload>
)
