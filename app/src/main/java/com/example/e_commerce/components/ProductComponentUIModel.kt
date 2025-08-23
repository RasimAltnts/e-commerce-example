package com.example.e_commerce.components

data class ProductComponentUIModel(
    var id: String,
    var name: String,
    var price: String,
    var model: String,
    var brand: String,
    var desc: String,
    var isFavorite: Boolean = false,
)
