package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.ProductLocalModel
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import com.example.e_commerce.domain.utils.enums.OrdersProcessEnum
import javax.inject.Inject

class UpdateOrdersUseCase @Inject constructor(
    val localProductRepository: LocalProductRepository
) {

    suspend operator fun invoke(orderId: String,type: OrdersProcessEnum) {
        val item = localProductRepository.getProduct(orderId)
        println("item::$item")
        when(type) {
            OrdersProcessEnum.MINUS -> minusProcess(item)
            OrdersProcessEnum.PLUS -> plusProcess(item)

        }
    }

    private suspend fun minusProcess(item: ProductLocalModel?) {
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



    private suspend fun plusProcess(item: ProductLocalModel?) {
        item?.let {
            it.count = it.count.plus(1)
            println("plusProcess::${it.count}")
            localProductRepository.updateProduct(it)
        }
    }
}