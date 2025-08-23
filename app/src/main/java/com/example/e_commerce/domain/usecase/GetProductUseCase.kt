package com.example.e_commerce.domain.usecase

import android.util.Printer
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.data.repository.local.LocalProductRepository
import com.example.e_commerce.data.repository.remote.ProductRepository
import com.example.e_commerce.utils.extension.toUIModel
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val localProductRepository: LocalProductRepository,
) {
    suspend operator fun invoke(): List<ProductComponentUIModel>? {
        val products = productRepository.getProducts()
        val favoriteProducts = localProductRepository.getFavoriteProducts()
        return products?.toUIModel(favoriteProducts)
    }
}