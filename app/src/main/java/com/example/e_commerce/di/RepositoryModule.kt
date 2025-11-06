package com.example.e_commerce.di

import com.example.e_commerce.domain.repository.remote.ProductRepository
import com.example.e_commerce.data.repository.local.LocalProductRepositoryImpl
import com.example.e_commerce.data.repository.remote.ProductRepositoryImpl
import com.example.e_commerce.domain.repository.local.LocalProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindLocalProductRepository(impl: LocalProductRepositoryImpl): LocalProductRepository
}