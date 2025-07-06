package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.models.WishlistProductItem
import com.example.supermarketmanager.databinding.ItemWishlistItemBinding

class WishlistAdapter(
    private val items: List<WishlistProductItem>,
    private val onRemove: (WishlistProductItem) -> Unit,
    private val onProductClick: (WishlistProductItem) -> Unit // ΝΕΟ
) : RecyclerView.Adapter<WishlistAdapter.VH>() {

    inner class VH(val binding: ItemWishlistItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWishlistItemBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val b = holder.binding

        // Όνομα
        b.tvProductName.text = item.product.name
        // Περιγραφή
        b.tvProductDescription.text = item.product.description
        // Τιμή + μονάδα
        b.tvProductPrice.text = "${item.product.pricePerUnit}€ / ${item.product.unit}"

        // Εικόνα (αν έχεις drawable):
        item.product.imageDrawable?.let {
            val context = b.ivProductImage.context
            val resId = context.resources.getIdentifier(it, "drawable", context.packageName)
            if (resId != 0) b.ivProductImage.setImageResource(resId)
            else b.ivProductImage.setImageResource(android.R.color.darker_gray) // fallback
        }

        // Offer badge
        b.tvProductOffer.apply {
            text = item.product.offer ?: ""
            visibility = if (!item.product.offer.isNullOrBlank()) View.VISIBLE else View.GONE
        }

        // CLICK LISTENERS
        b.root.setOnClickListener {
            onProductClick(item) // άνοιγμα λεπτομερειών!
        }
        // Προσθέτεις εδώ και το remove αν το έχεις:
        // π.χ. b.btnRemove.setOnClickListener { onRemove(item) }
    }

    override fun getItemCount(): Int = items.size
}
