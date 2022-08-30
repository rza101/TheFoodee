package com.rhezarijaya.core.data.local.room

import androidx.room.*
import com.rhezarijaya.core.data.local.entity.FavoriteFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDAO {
    @Query("SELECT * FROM tbl_favorite_food")
    fun getAllFavoriteFood(): Flow<List<FavoriteFoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)

    @Delete
    fun deleteFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)
}