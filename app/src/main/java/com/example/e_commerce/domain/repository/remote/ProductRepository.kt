package com.example.e_commerce.domain.repository.remote

import com.example.e_commerce.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): ProductModel?
}