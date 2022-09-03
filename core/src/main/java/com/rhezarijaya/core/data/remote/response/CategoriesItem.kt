package com.rhezarijaya.core.data.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesItem(
    @field:SerializedName("idCategory")
    val idCategory: String? = null,

    @field:SerializedName("strCategory")
    val strCategory: String? = null,

    @field:SerializedName("strCategoryThumb")
    val strCategoryThumb: String? = null,

    @field:SerializedName("strCategoryDescription")
    val strCategoryDescription: String? = null
)