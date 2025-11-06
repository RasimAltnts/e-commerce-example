package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import javax.inject.Inject

class GetAllOrdersUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {
    suspend operator fun invoke(): List<ProductLocalModel> {
        return localProductRepository.getAllProducts()
    }
}