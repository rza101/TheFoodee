package com.rhezarijaya.core.domain.usecase

import com.rhezarijaya.core.data.FoodRepository
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.domain.model.Food
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodInteractor @Inject constructor(private val foodRepository: FoodRepository) :
    IFoodUseCase {
    override fun addFavorite(food: Food) = foodRepository.addFavorite(food)

    override fun getCategories(): Flow<Resource<List<Category>>> = foodRepository.getCategories()

    override fun getFavorites(): Flow<Resource<List<Food>>> = foodRepository.getFavorites()

    override fun getFilteredByCategory(category: String): Flow<Resource<List<Food>>> =
        foodRepository.getFilteredByCategory(category)

    override fun getFoodDetail(foodId: String): Flow<Resource<Food>> =
        foodRepository.getFoodDetail(foodId)

    override fun removeFavorite(food: Food) = foodRepository.removeFavorite(food)

    override fun searchFood(query: String): Flow<Resource<List<Food>>> =
        foodRepository.searchFood(query)
}