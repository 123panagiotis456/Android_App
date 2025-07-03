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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWishlistItemBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        // Αν θέλεις να εμφανίζεις το όνομα του προϊόντος:
        // μπορείς να το κάνεις από εδώ αν έχεις πρόσβαση σε DAO ή να περάσεις επιπλέον δεδομένα
        // holder.binding.tvProductName.text = "Προϊόν ID: ${item.productId}"

        holder.binding.btnRemove.setOnClickListener {
            onRemove(item)
        }
    }

    override fun getItemCount(): Int = items.size
}

