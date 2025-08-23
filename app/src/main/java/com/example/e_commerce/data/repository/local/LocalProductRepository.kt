package com.example.e_commerce.data.repository.local

import com.example.e_commerce.data.local.entity.FavoriteEntity

interface LocalProductRepository {
    suspend fun getFavoriteProducts(): List<FavoriteEntity>?
    suspend fun addFavoriteProducts(item: FavoriteEntity)
    suspend fun deleteFavoriteProduct(item: FavoriteEntity)
}