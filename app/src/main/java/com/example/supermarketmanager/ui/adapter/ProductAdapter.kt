package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.databinding.ItemProductBinding

class ProductAdapter(
    private val items: List<ProductEntity>,
    private val onClick: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.binding.tvName.text = p.name
        holder.binding.tvPrice.text = "${p.pricePerUnit}€/${p.unit}"
        Glide.with(holder.binding.ivProduct).load(p.imageUrl).into(holder.binding.ivProduct)
        holder.binding.root.setOnClickListener { onClick(p) }
    }

    override fun getItemCount() = items.size
}
