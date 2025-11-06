package com.example.e_commerce.domain.usecase

import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val productLocalProductRepository: LocalProductRepository
) {
    suspend operator fun invoke(item: FavoriteEntity,state: Boolean) {
        if (state) productLocalProductRepository.deleteFavoriteProduct(item)
        else productLocalProductRepository.addFavoriteProducts(item)
    }
}