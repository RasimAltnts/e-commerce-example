package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.repository.local.LocalProductRepository
import com.example.e_commerce.ui.model.OrdersUiModel
import com.example.e_commerce.utils.extension.toOrdersUiModel
import javax.inject.Inject

class GetAllOrdersUseCase @Inject constructor(
    private val localProductRepository: LocalProductRepository
) {
    suspend operator fun invoke(): List<OrdersUiModel> {
        return localProductRepository.getAllProducts().toOrdersUiModel()
    }
}