package com.example.e_commerce.data.repository.local

import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface LocalProductRepository {
    suspend fun getFavoriteProducts(): List<FavoriteEntity>?
    suspend fun addFavoriteProducts(item: FavoriteEntity)
    suspend fun deleteFavoriteProduct(item: FavoriteEntity)

    suspend fun getAllProducts(): List<ProductEntity>
    suspend fun addProduct(item: ProductEntity)
    suspend fun deleteProduct(item: ProductEntity)
    suspend fun getProduct(id: String): ProductEntity?
    suspend fun updateProduct(product: ProductEntity)
    fun getAllProductsWithFlow(): Flow<List<ProductEntity>>

}