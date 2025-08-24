package com.example.e_commerce.domain.usecase

import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.repository.local.LocalProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {

    operator fun invoke(): Flow<List<ProductEntity>> {
        return localProductRepository.getAllProductsWithFlow()
    }
}