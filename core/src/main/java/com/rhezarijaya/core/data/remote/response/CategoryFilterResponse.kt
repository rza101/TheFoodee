package com.rhezarijaya.core.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryFilterResponse(
    @field:SerializedName("meals")
    val meals: List<FoodItem?>? = null
)
