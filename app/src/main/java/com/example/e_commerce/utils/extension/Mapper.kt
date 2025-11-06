package com.example.e_commerce.utils.extension

import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductDomainModel
import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.model.ProductsModel
import com.example.e_commerce.ui.model.OrdersUiModel

/**
 * Bu fonksiyon servisten alınan response u ui model e çevirme işlemi yapmaktadır.
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

/**
 * Bu fonsksion Product UI Modelden Favorite Entity modele mapper islemi yapmaktadir.
 */
fun ProductComponentUIModel.toFavoriteLocalModel(): FavoriteLocalModel {
    return FavoriteLocalModel(this.id)
}

/**
 * Bu fonsksion Product UI Modelden Product Entity modele mapper islemi yapmaktadir.
 */
fun ProductComponentUIModel.toProductEntity(): ProductLocalModel {
    return ProductLocalModel(
        id = this.id,
        name = this.name,
        price = this.price,
    )
}

/**
 * Bu fonsksion Product Entity Listesinden  OrdersUI Model Entity modele mapper islemi yapmaktadir.
 */
fun List<ProductLocalModel>.toOrdersUiModel(): List<OrdersUiModel> {
    return this.map {
        it.toOrderUiModel()
    }
}

/**
 * Bu fonsksion ProductEntity Modelden OrdersUIModel modele mapper islemi yapmaktadir.
 */
fun ProductLocalModel.toOrderUiModel() : OrdersUiModel {
    return OrdersUiModel(
        id = this.id,
        count = this.count,
        price = this.price,
        name = this.name
    )
}