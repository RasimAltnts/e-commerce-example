package com.example.e_commerce.data.repository.remote

import com.example.e_commerce.data.api.ProductApi
import com.example.e_commerce.data.mapper.toDomainModel
import com.example.e_commerce.domain.model.ProductDomainModel
import com.example.e_commerce.domain.repository.remote.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
): ProductRepository {
    override suspend fun getProducts(): List<ProductDomainModel>? = withContext(Dispatchers.IO) {
        val response = productApi.getProducts()
        if (response.isSuccessful) {
            return@withContext response.body()?.toDomainModel()
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }
}