package com.molks.ecommerce.di

import com.molks.ecommerce.data.demo_db.DemoDB
import com.molks.ecommerce.data.repository.ProductRepositoryImp
import com.molks.ecommerce.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideProductRepository(demoDB: DemoDB): ProductRepository {
        return ProductRepositoryImp(demoDB)
    }

}