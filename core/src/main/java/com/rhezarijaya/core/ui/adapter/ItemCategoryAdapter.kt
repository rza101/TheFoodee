package com.rhezarijaya.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhezarijaya.core.R
import com.rhezarijaya.core.databinding.ItemCategoryBinding
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.ui.OnItemClick

class ItemCategoryAdapter(private val onItemClick: OnItemClick<Category>) :
    ListAdapter<Category, ItemCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
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
                .load(data.strCategoryThumb)
                .placeholder(R.drawable.ic_baseline_dining_24)
                .error(R.drawable.ic_baseline_dining_24)
                .into(itemCategoryIvThumbnail)

            itemCategoryTvName.text = data.strCategory
        }

        holder.itemView.setOnClickListener {
            onItemClick.onClick(data)
        }
    }

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.idCategory.equals(newItem.idCategory)
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }
}