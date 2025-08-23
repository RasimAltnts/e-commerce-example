package com.example.e_commerce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerce.data.local.dao.ProductDao
import com.example.e_commerce.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductDao
}