package com.example.e_commerce.domain.usecase

import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.repository.local.LocalProductRepository
import com.example.e_commerce.ui.model.OrdersUiModel
import com.example.e_commerce.utils.enums.OrdersProcessEnum
import javax.inject.Inject

class UpdateOrdersUseCase @Inject constructor(
    val localProductRepository: LocalProductRepository
) {

    suspend operator fun invoke(model: OrdersUiModel,type: OrdersProcessEnum) {
        val item = localProductRepository.getProduct(model.id)
        when(type) {
            OrdersProcessEnum.MINUS -> minusProcess(item)
            OrdersProcessEnum.PLUS -> plusProcess(item)

        }
    }

    private suspend fun minusProcess(item: ProductEntity?) {
        item?.let {
            if (it.count == 1) {
                // Burda silme islemi yapilir. cunku count zaten 1 oldugu icin eksi yapilirsa localdan urun silinir.
                localProductRepository.deleteProduct(item)
            }

            else {
                it.count = it.count.minus(1)
                localProductRepository.updateProduct(it)
            }
        }
    }



    private suspend fun plusProcess(item: ProductEntity?) {
        item?.let {
            it.count = it.count.plus(1)
            localProductRepository.updateProduct(it)
        }
    }
}