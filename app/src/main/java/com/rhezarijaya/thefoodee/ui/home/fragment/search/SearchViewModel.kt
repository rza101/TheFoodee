package com.rhezarijaya.thefoodee.ui.home.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.usecase.IFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val foodUseCase: IFoodUseCase) : ViewModel() {
    private val searchQuery: MutableLiveData<String> = MutableLiveData()

    fun getSearchData() = Transformations.switchMap(searchQuery) {
        foodUseCase.searchFood(it).asLiveData()
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun addFavorite(food: Food) = foodUseCase.addFavorite(food)

    fun removeFavorite(food: Food) = foodUseCase.removeFavorite(food)
}