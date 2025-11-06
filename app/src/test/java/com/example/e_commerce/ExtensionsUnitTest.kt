package com.example.e_commerce

import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.data.model.ProductModel
import com.example.e_commerce.data.model.ProductModelItem
import com.example.e_commerce.utils.extension.toFavoriteEntity
import com.example.e_commerce.utils.extension.toOrderUiModel
import com.example.e_commerce.utils.extension.toUIModel
import org.junit.Test


class ExtensionsUnitTest {

    @Test
    fun testProductComponentUIModelToFavoriteEntity() {
        val productComponentUIModel = ProductComponentUIModel(
            id = "1",
            name = "Product 1",
            price = "10.0",
            isFavorite = true,
            model = "",
            brand = "",
            desc = "",
        )

        val favoriteEntity = productComponentUIModel.toFavoriteEntity()

        assert(favoriteEntity.id == productComponentUIModel.id)
    }

    @Test
    fun testProductEntityToOrderUIMoel() {
        val productEntity = ProductEntity(
            id = "1",
            name = "Product 1",
            price = "10.0",
            count = 1
        )

        val productComponentUIModel = productEntity.toOrderUiModel()

        assert(productComponentUIModel.id == productEntity.id)

    }

    @Test
    fun testProductModelToUIModelWithoutFavoriteModel() {
        val productModel = ProductModel()
        productModel.add(
            ProductModelItem(
                id = "1",
                name = "Product 1",
                price = "10.0",
                model = "",
                brand = "",
                description = "",
                createdAt = "",
                image =  ""
            )
        )

        val productComponentUIModel = productModel.toUIModel(emptyList())

        assert(productComponentUIModel.isNotEmpty())
    }

    @Test
    fun testProductModelToUIModelWithFavoriteModel() {
        val productModel = ProductModel()
        productModel.add(
            ProductModelItem(
                id = "1",
                name = "Product 1",
                price = "10.0",
                model = "",
                brand = "",
                description = "",
                createdAt = "",
                image =  ""
            )
        )

        productModel.add(
            ProductModelItem(
                id = "2",
                name = "Product 2",
                price = "10.0",
                model = "",
                brand = "",
                description = "",
                createdAt = "",
                image =  ""
            )
        )

        val favoriteModel = listOf(FavoriteEntity(id = "1"))

        val productComponentUIModel = productModel.toUIModel(favoriteModel)

        productComponentUIModel.forEach {
            if (it.id == favoriteModel.first().id) {
                assert(it.isFavorite)
            }
            else {
                assert(!it.isFavorite)
            }
        }
    }

    @Test
    fun testProductEntityToOrderUiModel() {
        val productEntity = ProductEntity(
            id = "1",
            name = "Product 1",
            price = "10.0",
            count = 1
        )

        val orderUiModel = productEntity.toOrderUiModel()

        assert(orderUiModel.id == productEntity.id)
        assert(orderUiModel.count == productEntity.count)
    }
}