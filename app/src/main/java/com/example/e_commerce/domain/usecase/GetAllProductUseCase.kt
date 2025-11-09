package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {

    operator fun invoke(): Flow<List<ProductLocalModel>> {
        return localProductRepository.getAllProductsWithFlow()
    }
}