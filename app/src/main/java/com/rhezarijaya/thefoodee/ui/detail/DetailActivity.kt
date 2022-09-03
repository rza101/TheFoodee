package com.rhezarijaya.thefoodee.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.ui.adapter.ItemIngredientAdapter
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.core.utils.DataMapper
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var foodId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(Constants.INTENT_ITEM_FOOD_TO_DETAIL)?.let {
            foodId = it
        } ?: run {
            Toast.makeText(this, getString(R.string.illegal_access), Toast.LENGTH_SHORT).show()
            finish()
        }

        var isFavoriteInstalled = true

        try {
            Class.forName("com.rhezarijaya.favorite.ui.favorite.FavoriteFragment")
        } catch (e: ClassNotFoundException) {
            isFavoriteInstalled = false
        }

        val itemIngredientAdapter = ItemIngredientAdapter()

        binding.apply {
            detailIvFavorite.visibility = View.GONE
            detailTvIngredientsTitle.visibility = View.GONE
            detailTvInstructionsTitle.visibility = View.GONE

            detailRvIngredients.apply {
                adapter = itemIngredientAdapter
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }
        }

        viewModel.getFoodDetail(foodId).observe(this) { foodResource ->
            binding.detailLottieLoading.visibility =
                if (foodResource is Resource.Loading) View.VISIBLE else View.GONE

            if (foodResource is Resource.Success) {
                foodResource.data?.let { food ->
                    binding.apply {
                        Glide.with(this@DetailActivity)
                            .load(food.strMealThumb ?: "")
                            .placeholder(R.drawable.ic_baseline_restaurant_menu_24)
                            .error(R.drawable.ic_baseline_restaurant_menu_24)
                            .into(detailIvThumbnail)

                        detailTvName.text = food.strMeal
                        detailTvCategory.text =
                            String.format(getString(R.string.category_format), food.strCategory)
                        detailTvArea.text =
                            String.format(getString(R.string.area_format), food.strArea)
                        detailTvInstructions.text = food.strInstructions

                        detailIvFavorite.visibility =
                            if (isFavoriteInstalled) View.VISIBLE else View.GONE
                        detailTvIngredientsTitle.visibility = View.VISIBLE
                        detailTvInstructionsTitle.visibility = View.VISIBLE

                        Glide.with(this@DetailActivity)
                            .load(
                                AppCompatResources.getDrawable(
                                    this@DetailActivity,
                                    if (food.isOnFavorite)
                                        com.rhezarijaya.core.R.drawable.ic_baseline_star_24
                                    else
                                        com.rhezarijaya.core.R.drawable.ic_baseline_star_outline_24
                                )?.toBitmap()
                            )
                            .into(detailIvFavorite)

                        detailIvFavorite.setOnClickListener {
                            if (food.isOnFavorite) {
                                viewModel.removeFavorite(food)
                            } else {
                                viewModel.addFavorite(food)
                            }
                        }
                    }

                    itemIngredientAdapter.submitList(DataMapper.domainFoodToIngredientsList(food))
                }
            }

            if (foodResource is Resource.Error) {
                Toast.makeText(
                    this,
                    String.format(getString(R.string.error_detail), foodResource.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}