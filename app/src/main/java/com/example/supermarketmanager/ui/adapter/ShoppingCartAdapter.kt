package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.CartPricing
import com.example.supermarketmanager.data.models.ShoppingCartItem
import com.example.supermarketmanager.databinding.ItemCartBinding
import com.example.supermarketmanager.ui.formatEuro
import com.example.supermarketmanager.ui.priceUnitLabel

class ShoppingCartAdapter(
    private val onIncreaseClick: (ShoppingCartItem) -> Unit,
    private val onDecreaseClick: (ShoppingCartItem) -> Unit
) : RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder>() {

    private var items: List<ShoppingCartItem> = emptyList()

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        val binding = holder.binding
        val context = binding.root.context
        binding.tvProductName.text = item.name
        binding.tvProductPrice.text = context.formatEuro(CartPricing.lineCents(item.pricePerUnit, item.quantity, item.offer) / 100.0)
        binding.tvProductUnitPrice.text =
            "${context.formatEuro(item.pricePerUnit)} ${context.priceUnitLabel(item.unit)}"
        binding.tvQuantity.text = item.quantity.toString()
        binding.tvProductOffer.isVisible = !item.offer.isNullOrBlank()
        binding.tvProductOffer.text = item.offer

        val imageId = item.imageDrawable?.let {
            context.resources.getIdentifier(it, "drawable", context.packageName)
        } ?: 0
        binding.ivProductImage.setImageResource(if (imageId != 0) imageId else R.drawable.ic_placeholder)

        binding.btnIncrease.setOnClickListener { onIncreaseClick(item) }
        binding.btnIncrease.isEnabled = item.quantity < item.availability
        binding.btnDecrease.setOnClickListener { onDecreaseClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<ShoppingCartItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
