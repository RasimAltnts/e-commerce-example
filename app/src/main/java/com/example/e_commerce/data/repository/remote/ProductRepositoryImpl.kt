package com.example.e_commerce.data.repository.remote

import com.example.e_commerce.data.api.ProductApi
import com.example.e_commerce.domain.model.ProductModel
import com.example.e_commerce.domain.repository.remote.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
): ProductRepository {
    override suspend fun getProducts(): ProductModel? = withContext(Dispatchers.IO) {
        val response = productApi.getProducts()
        if (response.isSuccessful) {
            return@withContext response.body()
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }
}