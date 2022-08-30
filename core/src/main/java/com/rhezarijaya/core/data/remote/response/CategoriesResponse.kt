package com.rhezarijaya.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @field:SerializedName("categories")
    val categories: List<CategoriesItem?>? = null
)
