package com.example.e_commerce.utils.extension

import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.model.ProductModel

/**
 * Bu fonksiyon servisten alınan response u ui model e çevirme işlemi yapmaktadır.
 */
fun ProductModel.toUIModel(): List<ProductComponentUIModel> {
    return this.map { item ->
        ProductComponentUIModel(
            id = item.id,
            name = item.name,
            price = item.price,
            model = item.model,
            brand = item.brand,
            desc = item.description
        )
    }
}
