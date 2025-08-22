package com.example.e_commerce.domain.usecase

import com.example.e_commerce.data.repository.ProductRepository
import com.example.e_commerce.domain.model.ProductModel
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): ProductModel? {
        return productRepository.getProducts()
    }
}