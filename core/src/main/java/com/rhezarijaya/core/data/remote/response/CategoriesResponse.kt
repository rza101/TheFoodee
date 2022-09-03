package com.rhezarijaya.core.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesResponse(
    @field:SerializedName("categories")
    val categories: List<CategoriesItem?>? = null
)
