package com.rhezarijaya.core.di

import com.rhezarijaya.core.data.FoodRepository
import com.rhezarijaya.core.domain.repository.IFoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [LocalDataSourceModule::class, RemoteDataSourceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(foodRepository: FoodRepository): IFoodRepository
}