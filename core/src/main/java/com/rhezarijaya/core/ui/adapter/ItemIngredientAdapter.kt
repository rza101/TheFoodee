package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemIngredientBinding
import com.rhezarijaya.core.domain.model.Ingredient

class ItemIngredientAdapter : ListAdapter<Ingredient, ItemIngredientAdapter.ViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(holder.adapterPosition)
        val context = holder.itemView.context

        holder.binding.apply {
            Glide.with(context)
                .load(data.thumbnail)
                .placeholder(R.drawable.ic_baseline_dining_24)
                .error(R.drawable.ic_baseline_dining_24)
                .fitCenter()
                .into(itemIngredientIvThumbnail)

            itemIngredientTvName.text = data.name
            itemIngredientTvMeasure.text = data.measure
        }
    }

    inner class ViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ingredient>() {
            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem == newItem
            }
        }
    }
}