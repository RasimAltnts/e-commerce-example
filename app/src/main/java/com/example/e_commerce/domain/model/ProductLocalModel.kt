package com.example.e_commerce.domain.model

data class ProductLocalModel(
    val id: String,
    val name: String,
    val price: String,
    var count: Int = 1,
)