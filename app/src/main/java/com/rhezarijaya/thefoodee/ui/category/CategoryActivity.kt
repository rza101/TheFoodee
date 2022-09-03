package com.rhezarijaya.thefoodee.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.ui.OnItemClick
import com.rhezarijaya.core.ui.adapter.ItemFoodAdapter
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.ActivityCategoryBinding
import com.rhezarijaya.thefoodee.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()

    private lateinit var categoryData: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Category>(Constants.INTENT_CATEGORY_LIST_TO_CATEGORY)?.let {
            categoryData = it
        } ?: run {
            Toast.makeText(this, getString(R.string.illegal_access), Toast.LENGTH_SHORT).show()
            finish()
        }

        val itemFoodAdapter = ItemFoodAdapter(
            object : OnItemClick<Food> {
                override fun onClick(data: Food) {
                    val intent = Intent(this@CategoryActivity, DetailActivity::class.java)
                    intent.putExtra(Constants.INTENT_ITEM_FOOD_TO_DETAIL, data.idMeal)

                    startActivity(intent)
                }
            }, object : OnItemClick<Food> {
                override fun onClick(data: Food) {
                    if (data.isOnFavorite) {
                        viewModel.removeFavorite(data)
                    } else {
                        viewModel.addFavorite(data)
                    }
                }
            }
        )

        binding.apply {
            Glide.with(this@CategoryActivity)
                .load(categoryData.strCategoryThumb ?: "")
                .placeholder(R.drawable.ic_baseline_restaurant_menu_24)
                .error(R.drawable.ic_baseline_restaurant_menu_24)
                .into(categoryIvThumbnail)

            categoryTvName.text = categoryData.strCategory
            categoryTvDescription.text = categoryData.strCategoryDescription

            categoryRv.apply {
                adapter = itemFoodAdapter
                layoutManager = LinearLayoutManager(this@CategoryActivity)
            }
        }

        viewModel.getFilteredByCategory(categoryData.strCategory ?: "")
            .observe(this) { foodResource ->
                binding.categoryLottieLoading.visibility =
                    if (foodResource is Resource.Loading) View.VISIBLE else View.GONE

                if (foodResource is Resource.Success) {
                    itemFoodAdapter.submitList(foodResource.data)
                }

                if (foodResource is Resource.Error) {
                    Toast.makeText(
                        this,
                        String.format(getString(R.string.error_category), foodResource.message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}