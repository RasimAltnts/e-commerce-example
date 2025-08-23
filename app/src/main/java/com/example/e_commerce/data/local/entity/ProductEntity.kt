package com.example.e_commerce.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: String,
    var count: Int = 1,
)