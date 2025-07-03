package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.CategoryEntity
import com.example.supermarketmanager.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val onClick: (CategoryEntity) -> Unit // 🔁 Πέρασμα ολόκληρης κατηγορίας
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categories: List<CategoryEntity> = emptyList()

    fun submitList(list: List<CategoryEntity>) {
        categories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryEntity) {
            binding.tvCategoryName.text = category.name

            val context = binding.root.context
            val imageResId = context.resources.getIdentifier(
                category.imageDrawable ?: "ic_launcher",
                "drawable",
                context.packageName
            )
            binding.ivCategoryIcon.setImageResource(imageResId)

            // 🔁 Πέρασμα όλης της κατηγορίας
            itemView.setOnClickListener {
                onClick(category)
            }
        }
    }
}
