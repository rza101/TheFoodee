package com.rhezarijaya.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.usecase.IFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel (private val foodUseCase: IFoodUseCase) : ViewModel() {
    fun getFavoriteList() = foodUseCase.getFavorites().asLiveData()

    fun addFavorite(food: Food) = foodUseCase.addFavorite(food)

    fun removeFavorite(food: Food) = foodUseCase.removeFavorite(food)
}