package com.example.e_commerce.domain.model

data class ProductsModel(
    var id: String,
    var name: String,
    var price: String,
    var model: String,
    var brand: String,
    var desc: String,
    var isFavorite: Boolean = false,
)