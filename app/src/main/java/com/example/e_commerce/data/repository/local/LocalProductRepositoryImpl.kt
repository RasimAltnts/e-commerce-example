package com.example.e_commerce.data.repository.local

import com.example.e_commerce.data.local.dao.ProductDao
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.mapper.toDomain
import com.example.e_commerce.data.mapper.toEntity
import com.example.e_commerce.data.mapper.toFavoriteLocalModel
import com.example.e_commerce.data.mapper.toProductLocalModel
import com.example.e_commerce.domain.model.FavoriteLocalModel
import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
): LocalProductRepository {

    override suspend fun getFavoriteProducts(): List<FavoriteLocalModel>? =
        withContext(Dispatchers.IO) {
            return@withContext productDao.getAllFavoriteProduct().toFavoriteLocalModel()
        }

    override suspend fun addFavoriteProducts(item: FavoriteLocalModel) = withContext(Dispatchers.IO) {
        return@withContext productDao.insertFavorite(item.toEntity())
    }

    override suspend fun deleteFavoriteProduct(item: FavoriteLocalModel) = withContext(Dispatchers.IO) {
        return@withContext productDao.deleteFavorite(item.toEntity())
    }

    override suspend fun getAllProducts(): List<ProductLocalModel> = withContext(Dispatchers.IO) {
        return@withContext productDao.getAllProduct().toProductLocalModel()
    }

    override suspend fun addProduct(item: ProductLocalModel) = withContext(Dispatchers.IO) {
        return@withContext productDao.insertProduct(item.toEntity())
    }

    override suspend fun deleteProduct(item: ProductLocalModel) = withContext(Dispatchers.IO) {
        return@withContext productDao.deleteProduct(item.toEntity())
    }

    override suspend fun getProduct(id: String): ProductLocalModel? = withContext(Dispatchers.IO) {
        return@withContext productDao.getProduct(id)?.toDomain()
    }

    override suspend fun updateProduct(product: ProductLocalModel) = withContext(Dispatchers.IO) {
        val entity = product.toEntity()
        println("entity::$entity")
        return@withContext productDao.updateProduct(entity)
    }

    override fun getAllProductsWithFlow(): Flow<List<ProductLocalModel>> =
        productDao.getAllProductsWithFlow().map { entities ->
            entities.toProductLocalModel()
        }
}