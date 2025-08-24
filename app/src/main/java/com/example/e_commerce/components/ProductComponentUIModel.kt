package com.example.e_commerce.components

import java.io.Serializable

data class ProductComponentUIModel(
    var id: String,
    var name: String,
    var price: String,
    var model: String,
    var brand: String,
    var desc: String,
    var isFavorite: Boolean = false,
): Serializable
