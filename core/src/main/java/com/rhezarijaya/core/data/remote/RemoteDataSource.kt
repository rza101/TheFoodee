package com.rhezarijaya.core.data.remote

import com.rhezarijaya.core.data.remote.network.APIResult
import com.rhezarijaya.core.data.remote.network.APIService
import com.rhezarijaya.core.data.remote.response.CategoriesResponse
import com.rhezarijaya.core.data.remote.response.CategoryFilterResponse
import com.rhezarijaya.core.data.remote.response.DetailLookupResponse
import com.rhezarijaya.core.data.remote.response.SearchResponse
import com.rhezarijaya.core.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    suspend fun getCategories(): Flow<APIResult<CategoriesResponse>> {
        return flow {
            try {
                val response = apiService.categories(Constants.API_KEY)

                response.categories?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailFood(foodId: String): Flow<APIResult<DetailLookupResponse>> {
        return flow {
            try {
                val response = apiService.detailFood(Constants.API_KEY, foodId)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFilteredByCategory(category: String): Flow<APIResult<CategoryFilterResponse>> {
        return flow {
            try {
                val response = apiService.filteredByCategory(Constants.API_KEY, category)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchFood(query: String): Flow<APIResult<SearchResponse>> {
        return flow {
            try {
                val response = apiService.searchFood(Constants.API_KEY, query)

                response.meals?.let {
                    emit(APIResult.Success(response))
                } ?: apply {
                    emit(APIResult.Empty)
                }
            } catch (e: Exception) {
                emit(APIResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}