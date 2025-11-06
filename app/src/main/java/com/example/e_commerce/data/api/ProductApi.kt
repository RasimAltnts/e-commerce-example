package com.example.e_commerce.data.api

import com.example.e_commerce.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getProducts(): Response<ProductModel>

}