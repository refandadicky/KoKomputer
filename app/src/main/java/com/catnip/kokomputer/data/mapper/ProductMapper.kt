package com.catnip.kokomputer.data.mapper

import com.catnip.kokomputer.data.model.Product
import com.catnip.kokomputer.data.source.network.model.products.ProductItemResponse

fun ProductItemResponse?.toProduct() =
    Product(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        rating = this?.rating ?: 0.0,
        price = this?.price ?: 0.0,
        imgUrl = this?.img_url.orEmpty(),
        desc = this?.desc.orEmpty()
    )

fun Collection<ProductItemResponse>?.toProducts() = this?.map {
    it.toProduct()
} ?: listOf()