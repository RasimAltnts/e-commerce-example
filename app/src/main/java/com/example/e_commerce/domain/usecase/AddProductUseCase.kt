package com.example.e_commerce.domain.usecase

import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.repository.local.LocalProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository,
) {

    suspend operator fun invoke(item: ProductEntity) {
        // Bu kisimda daha once ayni id eklenmis ise count i 1 arttirip kayit guncellenmeli
        val product = localProductRepository.getProduct(item.id)
        if (product != null) {
            product.count++
            localProductRepository.addProduct(product)
        }
        else localProductRepository.addProduct(item)
    }
}