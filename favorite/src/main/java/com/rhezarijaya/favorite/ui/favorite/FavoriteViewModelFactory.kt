package com.rhezarijaya.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhezarijaya.core.domain.usecase.IFoodUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(private val foodUseCase: IFoodUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(foodUseCase) as T
    }
}