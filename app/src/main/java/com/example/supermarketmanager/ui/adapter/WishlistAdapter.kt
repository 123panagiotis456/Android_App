package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.databinding.ItemWishlistItemBinding

class WishlistAdapter(
    private val items: List<WishlistItemEntity>,
    private val onRemove: (WishlistItemEntity) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.VH>() {

    inner class VH(val binding: ItemWishlistItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemWishlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        // binding.tvProductName.text = ... fetch product by ID if needed
        holder.binding.btnRemove.setOnClickListener { onRemove(item) }
    }

    override fun getItemCount() = items.size
}
