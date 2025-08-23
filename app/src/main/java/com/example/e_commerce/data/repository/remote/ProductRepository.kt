package com.example.e_commerce.data.repository.remote

import com.example.e_commerce.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): ProductModel?
}