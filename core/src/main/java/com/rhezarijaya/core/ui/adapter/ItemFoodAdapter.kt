package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemFoodBinding
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.ui.OnItemClick

class ItemFoodAdapter(
    private val onItemClick: OnItemClick<Food>,
    private val onFavoriteClick: OnItemClick<Food>
) :
    ListAdapter<Food, ItemFoodAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(holder.adapterPosition)
        val context = holder.itemView.context
        var isFavoriteInstalled = true

        try {
            Class.forName("com.rhezarijaya.favorite.ui.favorite.FavoriteFragment")
        } catch (e: ClassNotFoundException) {
            isFavoriteInstalled = false
        }

        holder.itemView.setOnClickListener {
            onItemClick.onClick(data)
        }

        holder.binding.apply {
            Glide.with(context)
                .load(data.strMealThumb)
                .placeholder(R.drawable.ic_baseline_dining_24)
                .error(R.drawable.ic_baseline_dining_24)
                .fitCenter()
                .into(itemFoodIvThumbnail)

            itemFoodTvName.text = data.strMeal

            itemFoodIvFavorite.visibility = if (isFavoriteInstalled) View.VISIBLE else View.GONE

            Glide.with(context)
                .load(
                    AppCompatResources.getDrawable(
                        context,
                        if (data.isOnFavorite)
                            R.drawable.ic_baseline_star_24
                        else
                            R.drawable.ic_baseline_star_outline_24
                    )?.toBitmap()
                )
                .into(itemFoodIvFavorite)

            itemFoodIvFavorite.setOnClickListener {
                onFavoriteClick.onClick(data)
            }
        }
    }

    inner class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }
}