package com.rhezarijaya.thefoodee.di

import com.rhezarijaya.core.domain.usecase.IFoodUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun foodUseCase(): IFoodUseCase
}