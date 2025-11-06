package com.example.e_commerce.data.mapper

import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.model.ProductModel
import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductDomainModel
import com.example.e_commerce.domain.model.ProductLocalModel


fun List<FavoriteEntity>.toFavoriteLocalModel(): List<FavoriteLocalModel> {
    return this.map {
        FavoriteLocalModel(
            id = it.id
        )
    }
}

fun List<ProductEntity>.toProductLocalModel(): List<ProductLocalModel> {
    return this.map {
        ProductLocalModel(
            id = it.id,
            name = it.name,
            price = it.price,
            count = it.count
        )
    }
}

fun ProductEntity.toDomain(): ProductLocalModel {
    return ProductLocalModel(
        id = this.id,
        name = this.name,
        price = this.price,
        count = this.count
    )
}

fun FavoriteLocalModel.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id
    )
}

fun ProductLocalModel.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        count = this.count
    )
}

fun ProductModel.toDomainModel(): List<ProductDomainModel> {
    return this.map {
        ProductDomainModel(
            id = it.id,
            name = it.name,
            price = it.price,
            model = it.model,
            brand = it.brand,
            desc = it.description
        )
    }
}