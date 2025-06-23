package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.databinding.ItemShoppingListItemBinding

class ShoppingListAdapter(
    private val items: List<ShoppingListItemEntity>,
    private val onRemove: (ShoppingListItemEntity) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.VH>() {

    inner class VH(val binding: ItemShoppingListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemShoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        // assume you have binding.tvName, binding.tvQuantity
        holder.binding.tvQuantity.text = item.quantity.toString()
        holder.binding.btnRemove.setOnClickListener { onRemove(item) }
    }

    override fun getItemCount() = items.size
}
