package com.example.e_commerce.domain.repository.local

import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductLocalModel
import kotlinx.coroutines.flow.Flow

interface LocalProductRepository {
    suspend fun getFavoriteProducts(): List<FavoriteLocalModel>?
    suspend fun addFavoriteProducts(item: FavoriteLocalModel)
    suspend fun deleteFavoriteProduct(item: FavoriteLocalModel)

    suspend fun getAllProducts(): List<ProductLocalModel>
    suspend fun addProduct(item: ProductLocalModel)
    suspend fun deleteProduct(item: ProductLocalModel)
    suspend fun getProduct(id: String): ProductLocalModel?
    suspend fun updateProduct(product: ProductLocalModel)
    fun getAllProductsWithFlow(): Flow<List<ProductEntity>>

}