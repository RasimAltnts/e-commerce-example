package com.example.e_commerce.data.repository

import com.example.e_commerce.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): ProductModel?
}