package com.rhezarijaya.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("meals")
    val meals: List<FoodItem?>? = null
)

