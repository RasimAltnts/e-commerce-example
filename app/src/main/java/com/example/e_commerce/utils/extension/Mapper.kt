package com.example.e_commerce.utils.extension

import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.domain.model.ProductModel
import com.example.e_commerce.ui.model.OrdersUiModel

/**
 * Bu fonksiyon servisten alınan response u ui model e çevirme işlemi yapmaktadır.
 */
fun ProductModel.toUIModel(favoriteList: List<FavoriteEntity>?): List<ProductComponentUIModel> {
    return this.map { item ->
        ProductComponentUIModel(
            id = item.id,
            name = item.name,
            price = item.price,
            model = item.model,
            brand = item.brand,
            desc = item.description,
            isFavorite = favoriteList?.any { it.id == item.id } ?: false
        )
    }
}

/**
 * Bu fonsksion Product UI Modelden Favorite Entity modele mapper islemi yapmaktadir.
 */
fun ProductComponentUIModel.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(this.id)
}

/**
 * Bu fonsksion Product UI Modelden Product Entity modele mapper islemi yapmaktadir.
 */
fun ProductComponentUIModel.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        price = this.price,
    )
}

/**
 * Bu fonsksion Product Entity Listesinden  OrdersUI Model Entity modele mapper islemi yapmaktadir.
 */
fun List<ProductEntity>.toOrdersUiModel(): List<OrdersUiModel> {
    return this.map {
        it.toOrderUiModel()
    }
}

/**
 * Bu fonsksion ProductEntity Modelden OrdersUIModel modele mapper islemi yapmaktadir.
 */
fun ProductEntity.toOrderUiModel() : OrdersUiModel {
    return OrdersUiModel(
        id = this.id,
        count = this.count,
        price = this.price,
        name = this.name
    )
}
