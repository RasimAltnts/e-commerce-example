package com.example.e_commerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerce.data.local.dao.ProductDao
import com.example.e_commerce.data.local.entity.FavoriteEntity
import com.example.e_commerce.data.local.entity.ProductEntity

@Database(entities =
    [
        FavoriteEntity::class,
        ProductEntity::class
    ],
    version = 3, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductDao
}