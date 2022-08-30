package com.rhezarijaya.thefoodee.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.usecase.IFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val foodUseCase: IFoodUseCase) : ViewModel() {
    fun getFoodDetail(foodId: String) = foodUseCase.getFoodDetail(foodId).asLiveData()

    fun addFavorite(food: Food) = foodUseCase.addFavorite(food)

    fun removeFavorite(food: Food) = foodUseCase.removeFavorite(food)
}