package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.CategoryEntity
import com.example.supermarketmanager.databinding.ItemCategoryBinding

class CategoryAdapter
    : ListAdapter<CategoryEntity, CategoryAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<CategoryEntity>() {
            override fun areItemsTheSame(old: CategoryEntity, new: CategoryEntity) =
                old.id == new.id

            override fun areContentsTheSame(old: CategoryEntity, new: CategoryEntity) =
                old == new
        }
    }

    inner class VH(private val binding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryEntity) {
            binding.tvCategoryName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
