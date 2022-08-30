package com.rhezarijaya.core.utils

import com.rhezarijaya.core.data.local.entity.FavoriteFoodEntity
import com.rhezarijaya.core.data.remote.response.CategoriesItem
import com.rhezarijaya.core.data.remote.response.FoodItem
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.domain.model.Ingredient
import java.net.URI

object DataMapper {
    fun dataCategoryToDomainCategory(categoryItem: CategoriesItem): Category {
        return Category(
            idCategory = categoryItem.idCategory,
            strCategory = categoryItem.strCategory,
            strCategoryThumb = categoryItem.strCategoryThumb,
            strCategoryDescription = categoryItem.strCategoryDescription
        )
    }

    fun dataFoodToDomainFood(foodItem: FoodItem, isOnFavorite: Boolean): Food {
        return Food(
            idMeal = foodItem.idMeal,
            strMeal = foodItem.strMeal,
            strDrinkAlternate = foodItem.strDrinkAlternate,
            strCategory = foodItem.strCategory,
            strArea = foodItem.strArea,
            strInstructions = foodItem.strInstructions,
            strMealThumb = foodItem.strMealThumb,
            strTags = foodItem.strTags,
            strYoutube = foodItem.strYoutube,
            strIngredient1 = foodItem.strIngredient1,
            strIngredient2 = foodItem.strIngredient2,
            strIngredient3 = foodItem.strIngredient3,
            strIngredient4 = foodItem.strIngredient4,
            strIngredient5 = foodItem.strIngredient5,
            strIngredient6 = foodItem.strIngredient6,
            strIngredient7 = foodItem.strIngredient7,
            strIngredient8 = foodItem.strIngredient8,
            strIngredient9 = foodItem.strIngredient9,
            strIngredient10 = foodItem.strIngredient10,
            strIngredient11 = foodItem.strIngredient11,
            strIngredient12 = foodItem.strIngredient12,
            strIngredient13 = foodItem.strIngredient13,
            strIngredient14 = foodItem.strIngredient14,
            strIngredient15 = foodItem.strIngredient15,
            strIngredient16 = foodItem.strIngredient16,
            strIngredient17 = foodItem.strIngredient17,
            strIngredient18 = foodItem.strIngredient18,
            strIngredient19 = foodItem.strIngredient19,
            strIngredient20 = foodItem.strIngredient20,
            strMeasure1 = foodItem.strMeasure1,
            strMeasure2 = foodItem.strMeasure2,
            strMeasure3 = foodItem.strMeasure3,
            strMeasure4 = foodItem.strMeasure4,
            strMeasure5 = foodItem.strMeasure5,
            strMeasure6 = foodItem.strMeasure6,
            strMeasure7 = foodItem.strMeasure7,
            strMeasure8 = foodItem.strMeasure8,
            strMeasure9 = foodItem.strMeasure9,
            strMeasure10 = foodItem.strMeasure10,
            strMeasure11 = foodItem.strMeasure11,
            strMeasure12 = foodItem.strMeasure12,
            strMeasure13 = foodItem.strMeasure13,
            strMeasure14 = foodItem.strMeasure14,
            strMeasure15 = foodItem.strMeasure15,
            strMeasure16 = foodItem.strMeasure16,
            strMeasure17 = foodItem.strMeasure17,
            strMeasure18 = foodItem.strMeasure18,
            strMeasure19 = foodItem.strMeasure19,
            strMeasure20 = foodItem.strMeasure20,
            strSource = foodItem.strSource,
            strImageSource = foodItem.strImageSource,
            strCreativeCommonsConfirmed = foodItem.strImageSource,
            dateModified = foodItem.strImageSource,
            isOnFavorite = isOnFavorite
        )
    }

    fun domainFoodToEntityFood(food: Food): FavoriteFoodEntity {
        return FavoriteFoodEntity(
            id = food.idMeal,
            name = food.strMeal,
            thumbnail = food.strMealThumb ?: ""
        )
    }

    fun entityFoodToDomainFood(favoriteFoodEntity: FavoriteFoodEntity): Food {
        return Food(
            idMeal = favoriteFoodEntity.id,
            strMeal = favoriteFoodEntity.name,
            strMealThumb = favoriteFoodEntity.thumbnail,
            isOnFavorite = true // karena dari database local pasti adalah favorite
        )
    }

    fun domainFoodToIngredientsList(food: Food): List<Ingredient> {
        val list: MutableList<Ingredient> = mutableListOf()

        food.strIngredient1?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure1))
            }
        }

        food.strIngredient2?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure2))
            }
        }

        food.strIngredient3?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure3))
            }
        }

        food.strIngredient4?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure4))
            }
        }

        food.strIngredient5?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure5))
            }
        }

        food.strIngredient6?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure6))
            }
        }

        food.strIngredient7?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure7))
            }
        }

        food.strIngredient8?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure8))
            }
        }

        food.strIngredient9?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure9))
            }
        }

        food.strIngredient10?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure10))
            }
        }

        food.strIngredient11?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure11))
            }
        }

        food.strIngredient12?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure12))
            }
        }

        food.strIngredient13?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure13))
            }
        }

        food.strIngredient14?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure14))
            }
        }

        food.strIngredient15?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure15))
            }
        }

        food.strIngredient16?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure16))
            }
        }

        food.strIngredient17?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure17))
            }
        }

        food.strIngredient18?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure18))
            }
        }

        food.strIngredient19?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure19))
            }
        }

        food.strIngredient20?.let {
            if (it != "") {
                list.add(Ingredient(it, ingredientNameToImageUrl(it), food.strMeasure20))
            }
        }

        return list
    }

    private fun ingredientNameToImageUrl(name: String): String {
        return URI(
            "https",
            "www.themealdb.com",
            "/images/ingredients/${name}.png",
            null
        ).toString()
    }
}