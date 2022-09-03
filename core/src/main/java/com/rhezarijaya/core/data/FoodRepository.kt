package com.rhezarijaya.core.data

import com.rhezarijaya.core.data.local.LocalDataSource
import com.rhezarijaya.core.data.local.entity.FavoriteFoodEntity
import com.rhezarijaya.core.data.remote.RemoteDataSource
import com.rhezarijaya.core.data.remote.network.APIResult
import com.rhezarijaya.core.data.remote.response.CategoriesItem
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.repository.IFoodRepository
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.core.utils.DataMapper
import com.rhezarijaya.core.utils.DatabaseExecutor
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class FoodRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val databaseExecutor: DatabaseExecutor
) : IFoodRepository {
    override fun addFavorite(food: Food) {
        databaseExecutor.diskIO().execute {
            localDataSource.insertFavoriteFood(DataMapper.domainFoodToEntityFood(food))
        }
    }

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getCategories().first()) {
                is APIResult.Success -> {
                    result.data.categories?.let { categoriesItem ->
                        emit(Resource.Success(categoriesItem.map { categoryItem ->
                            DataMapper.dataCategoryToDomainCategory(
                                categoryItem ?: CategoriesItem()
                            )
                        }))
                    }
                }
                is APIResult.Empty -> {
                    emit(Resource.Error(Constants.EMPTY_DATA))
                }
                is APIResult.Error -> {
                    try {
                        emit(Resource.Error((result.exception as HttpException).message()))
                    } catch (e: Exception) {
                        // gagal cast ke HttpException
                        emit(Resource.Error(result.exception.message ?: Constants.UNKNOWN_ERROR))
                    }
                }
            }
        }
    }

    override fun getFavorites(): Flow<Resource<List<Food>>> {
        return flow {
            emit(Resource.Loading())

            localDataSource.getAllFavoriteFood().map { listFavorite ->
                listFavorite.map { favorite ->
                    DataMapper.entityFoodToDomainFood(favorite)
                }
            }.collect {
                emit(Resource.Success(it))
            }
        }
    }

    override fun getFilteredByCategory(category: String): Flow<Resource<List<Food>>> {
        return flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getFilteredByCategory(category).first()) {
                is APIResult.Success -> {
                    localDataSource.getAllFavoriteFood()
                        .combine(flowOf(result.data.meals)) { favorite, filteredByCategory ->
                            filteredByCategory?.map { foodItem ->
                                foodItem?.let {
                                    DataMapper.dataFoodToDomainFood(
                                        it, favorite.contains(
                                            FavoriteFoodEntity(
                                                it.idMeal,
                                                it.strMeal,
                                                it.strMealThumb
                                            )
                                        )
                                    )
                                }
                            }
                        }.collect {
                            emit(Resource.Success(it as List<Food>))
                        }
                }
                is APIResult.Empty -> {
                    emit(Resource.Error(Constants.EMPTY_DATA))
                }
                is APIResult.Error -> {
                    try {
                        emit(Resource.Error((result.exception as HttpException).message()))
                    } catch (e: Exception) {
                        // gagal cast ke HttpException
                        emit(Resource.Error(result.exception.message ?: Constants.UNKNOWN_ERROR))
                    }
                }
            }
        }
    }

    override fun getFoodDetail(foodId: String): Flow<Resource<Food>> {
        return flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.getDetailFood(foodId).first()) {
                is APIResult.Success -> {
                    localDataSource.getAllFavoriteFood()
                        .combine(flowOf(result.data.meals)) { favorite, filteredByCategory ->
                            filteredByCategory?.map { foodItem ->
                                foodItem?.let {
                                    DataMapper.dataFoodToDomainFood(
                                        it, favorite.contains(
                                            FavoriteFoodEntity(
                                                it.idMeal,
                                                it.strMeal,
                                                it.strMealThumb
                                            )
                                        )
                                    )
                                }
                            }
                        }.collect {
                            it?.get(0)?.let { it2 ->
                                emit(Resource.Success(it2))
                            }
                        }
                }
                is APIResult.Empty -> {
                    emit(Resource.Error(Constants.EMPTY_DATA))
                }
                is APIResult.Error -> {
                    try {
                        emit(Resource.Error((result.exception as HttpException).message()))
                    } catch (e: Exception) {
                        // gagal cast ke HttpException
                        emit(Resource.Error(result.exception.message ?: Constants.UNKNOWN_ERROR))
                    }
                }
            }
        }
    }

    override fun removeFavorite(food: Food) {
        databaseExecutor.diskIO().execute {
            localDataSource.deleteFavoriteFood(DataMapper.domainFoodToEntityFood(food))
        }
    }

    override fun searchFood(query: String): Flow<Resource<List<Food>>> {
        return flow {
            emit(Resource.Loading())

            when (val result = remoteDataSource.searchFood(query).first()) {
                is APIResult.Success -> {
                    localDataSource.getAllFavoriteFood()
                        .combine(flowOf(result.data.meals)) { favorite, filteredByCategory ->
                            filteredByCategory?.map { foodItem ->
                                foodItem?.let {
                                    DataMapper.dataFoodToDomainFood(
                                        it, favorite.contains(
                                            FavoriteFoodEntity(
                                                it.idMeal,
                                                it.strMeal,
                                                it.strMealThumb
                                            )
                                        )
                                    )
                                }
                            }
                        }.collect {
                            emit(Resource.Success(it as List<Food>))
                        }
                }
                is APIResult.Empty -> {
                    emit(Resource.Error(Constants.EMPTY_DATA))
                }
                is APIResult.Error -> {
                    try {
                        emit(Resource.Error((result.exception as HttpException).message()))
                    } catch (e: Exception) {
                        // gagal cast ke HttpException
                        emit(Resource.Error(result.exception.message ?: Constants.UNKNOWN_ERROR))
                    }
                }
            }
        }
    }
}