package com.rhezarijaya.core.data.remote.network

import com.rhezarijaya.core.data.remote.response.CategoriesResponse
import com.rhezarijaya.core.data.remote.response.CategoryFilterResponse
import com.rhezarijaya.core.data.remote.response.DetailLookupResponse
import com.rhezarijaya.core.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("{api_key}/categories.php")
    suspend fun categories(
        @Path("api_key") apiKey: String
    ): CategoriesResponse

    @GET("{api_key}/lookup.php")
    suspend fun detailFood(
        @Path("api_key") apiKey: String,
        @Query("i") foodId: String
    ): DetailLookupResponse

    @GET("{api_key}/filter.php")
    suspend fun filteredByCategory(
        @Path("api_key") apiKey: String,
        @Query("c") category: String
    ): CategoryFilterResponse

    @GET("{api_key}/search.php")
    suspend fun searchFood(
        @Path("api_key") apiKey: String,
        @Query("s") query: String
    ): SearchResponse
}