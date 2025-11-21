package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.ProductsModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import com.example.e_commerce.domain.repository.remote.ProductRepository
import com.example.e_commerce.domain.utils.mapper.toMerge
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val localProductRepository: LocalProductRepository,
) {
    suspend operator fun invoke(): List<ProductsModel>? {
        val products = productRepository.getProducts()
        val favoriteProducts = localProductRepository.getFavoriteProducts()
        return products?.toMerge(favoriteProducts)
    }
}