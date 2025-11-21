package com.example.e_commerce.domain.utils.mapper

import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductDomainModel
import com.example.e_commerce.domain.model.ProductsModel

/**
 * Bu fonksiyon servisten alınan response u domain model e çevirme işlemi yapmaktadır.
 */
fun List<ProductDomainModel>.toMerge(favoriteList: List<FavoriteLocalModel>?): List<ProductsModel> {
    return this.map { item ->
        ProductsModel(
            id = item.id,
            name = item.name,
            price = item.price,
            model = item.model,
            brand = item.brand,
            desc = item.desc,
            isFavorite = favoriteList?.any { it.id == item.id } ?: false
        )
    }
}

