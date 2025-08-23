package com.example.e_commerce.di

import android.content.Context
import androidx.room.Room
import com.example.e_commerce.data.local.Database
import com.example.e_commerce.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: Database): ProductDao = database.productDao()
}