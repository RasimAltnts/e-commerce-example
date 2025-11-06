package com.example.e_commerce.domain.repository.remote

import com.example.e_commerce.domain.model.ProductDomainModel

interface ProductRepository {
    suspend fun getProducts(): List<ProductDomainModel>?
}