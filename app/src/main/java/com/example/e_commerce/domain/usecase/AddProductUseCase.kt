package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository,
) {

    suspend operator fun invoke(item: ProductLocalModel) {
        // Bu kisimda daha once ayni id eklenmis ise count i 1 arttirip kayit guncellenmeli
        val product = localProductRepository.getProduct(item.id)
        if (product != null) {
            product.count++
            localProductRepository.addProduct(product)
        }
        else localProductRepository.addProduct(item)
    }
}