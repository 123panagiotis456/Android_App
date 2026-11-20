package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.models.WishlistProductItem
import com.example.supermarketmanager.databinding.ItemWishlistItemBinding
import com.example.supermarketmanager.ui.formatEuro
import com.example.supermarketmanager.ui.priceUnitLabel

class WishlistAdapter(
    private val onRemove: (WishlistProductItem) -> Unit,
    private val onProductClick: (WishlistProductItem) -> Unit
) : ListAdapter<WishlistProductItem, WishlistAdapter.ViewHolder>(WishlistDiff) {

    class ViewHolder(val binding: ItemWishlistItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWishlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val product = item.product
        val binding = holder.binding
        val context = binding.root.context

        binding.tvProductName.text = product.name
        binding.tvProductDescription.text = product.description
        binding.tvProductPrice.text = context.formatEuro(product.pricePerUnit)
        binding.tvProductUnit.text = context.priceUnitLabel(product.unit)
        binding.tvProductOffer.text = product.offer
        binding.tvProductOffer.isVisible = !product.offer.isNullOrBlank()

        val imageId = product.imageDrawable?.let {
            context.resources.getIdentifier(it, "drawable", context.packageName)
        } ?: 0
        binding.ivProductImage.setImageResource(if (imageId != 0) imageId else R.drawable.ic_placeholder)

        binding.root.setOnClickListener { onProductClick(item) }
        binding.btnRemoveFavorite.contentDescription =
            context.getString(R.string.wishlist_remove_product, product.name)
        binding.btnRemoveFavorite.setOnClickListener { onRemove(item) }
    }

    private object WishlistDiff : DiffUtil.ItemCallback<WishlistProductItem>() {
        override fun areItemsTheSame(oldItem: WishlistProductItem, newItem: WishlistProductItem) =
            oldItem.wishlist.id == newItem.wishlist.id

        override fun areContentsTheSame(oldItem: WishlistProductItem, newItem: WishlistProductItem) =
            oldItem == newItem
    }
}
