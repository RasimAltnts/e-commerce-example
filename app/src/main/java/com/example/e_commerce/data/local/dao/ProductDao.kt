package com.example.e_commerce.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAllFavoriteProduct(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM products")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: String): ProductEntity?

    @Update
    suspend fun updateProduct(product: ProductEntity)

}