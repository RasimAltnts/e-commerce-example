package com.example.e_commerce.ui.utils.mapper

import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.ui.model.OrdersUiModel

/**
 * Bu fonksiyon Product UI Modelden Favorite Domain modele mapper işlemi yapmaktadır.
 */
fun ProductComponentUIModel.toFavoriteLocalModel(): FavoriteLocalModel {
    return FavoriteLocalModel(this.id)
}

/**
 * Bu fonksiyon Product UI Modelden Product Domain modele mapper işlemi yapmaktadır.
 */
fun ProductComponentUIModel.toProductLocalModel(): ProductLocalModel {
    return ProductLocalModel(
        id = this.id,
        name = this.name,
        price = this.price,
    )
}

/**
 * Bu fonksiyon Product Domain Model Listesinden OrdersUI Model'e mapper işlemi yapmaktadır.
 */
fun List<ProductLocalModel>.toOrdersUiModel(): List<OrdersUiModel> {
    return this.map {
        it.toOrderUiModel()
    }
}

/**
 * Bu fonksiyon Product Domain Modelden OrdersUIModel'e mapper işlemi yapmaktadır.
 */
fun ProductLocalModel.toOrderUiModel(): OrdersUiModel {
    return OrdersUiModel(
        id = this.id,
        count = this.count,
        price = this.price,
        name = this.name
    )
}

