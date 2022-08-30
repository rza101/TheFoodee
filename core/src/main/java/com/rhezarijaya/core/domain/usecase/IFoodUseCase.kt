package com.rhezarijaya.core.domain.usecase

import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodUseCase {
    fun addFavorite(food: Food)

    fun getCategories(): Flow<Resource<List<Category>>>

    fun getFavorites(): Flow<Resource<List<Food>>>

    fun getFilteredByCategory(category: String): Flow<Resource<List<Food>>>

    fun getFoodDetail(foodId: String): Flow<Resource<Food>>

    fun removeFavorite(food: Food)

    fun searchFood(query: String): Flow<Resource<List<Food>>>
}