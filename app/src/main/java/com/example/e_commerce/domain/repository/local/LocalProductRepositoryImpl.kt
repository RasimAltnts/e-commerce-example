package com.example.e_commerce.domain.repository.local

import com.example.e_commerce.data.local.dao.ProductDao
import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.repository.local.LocalProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
): LocalProductRepository {

    override suspend fun getFavoriteProducts(): List<FavoriteEntity>? =
        withContext(Dispatchers.IO) {
            return@withContext productDao.getAllFavoriteProduct()
        }

    override suspend fun addFavoriteProducts(item: FavoriteEntity) {
        withContext(Dispatchers.IO) {
            return@withContext productDao.insertFavorite(item)
        }
    }

    override suspend fun deleteFavoriteProduct(item: FavoriteEntity) = withContext(Dispatchers.IO) {
        return@withContext productDao.deleteFavorite(item)
    }

    override suspend fun getAllProducts(): List<ProductEntity> = withContext(Dispatchers.IO) {
        return@withContext productDao.getAllProduct()
    }

    override suspend fun addProduct(item: ProductEntity) = withContext(Dispatchers.IO) {
        return@withContext productDao.insertProduct(item)
    }

    override suspend fun deleteProduct(item: ProductEntity) = withContext(Dispatchers.IO) {
        return@withContext productDao.deleteProduct(item)
    }

    override suspend fun getProduct(id: String): ProductEntity? = withContext(Dispatchers.IO) {
        return@withContext productDao.getProduct(id)
    }
}