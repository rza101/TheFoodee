package com.rhezarijaya.core.data.local

import com.rhezarijaya.core.data.local.entity.FavoriteFoodEntity
import com.rhezarijaya.core.data.local.room.FavoriteFoodDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val favoriteFoodDAO: FavoriteFoodDAO) {
    fun getAllFavoriteFood(): Flow<List<FavoriteFoodEntity>> = favoriteFoodDAO.getAllFavoriteFood()

    fun insertFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity) =
        favoriteFoodDAO.insertFavoriteFood(favoriteFoodEntity)

    fun deleteFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity) =
        favoriteFoodDAO.deleteFavoriteFood(favoriteFoodEntity)
}