package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.models.ShoppingCartItem

class ShoppingCartAdapter(
    private val items: MutableList<ShoppingCartItem> = mutableListOf(),
    private val onIncreaseClick: (ShoppingCartItem) -> Unit,
    private val onDecreaseClick: (ShoppingCartItem) -> Unit
) : RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.tvProductName)
        val priceText: TextView = view.findViewById(R.id.tvProductPrice)
        val descriptionText: TextView = view.findViewById(R.id.tvProductDescription)
        val imageView: ImageView = view.findViewById(R.id.ivProductImage)
        val offerText: TextView = view.findViewById(R.id.tvProductOffer)
        val quantityText: TextView = view.findViewById(R.id.tvQuantity)
        val btnIncrease: ImageButton = view.findViewById(R.id.btnIncrease)
        val btnDecrease: ImageButton = view.findViewById(R.id.btnDecrease)
        // val pricePerUnitText: TextView = view.findViewById(R.id.tvProductPricePerUnit) // Αν το προσθέσεις στο XML
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.priceText.text = "€%.2f".format(item.pricePerUnit * item.quantity)
        holder.quantityText.text = item.quantity.toString()

        item.imageDrawable?.let {
            val context = holder.itemView.context
            val resId = context.resources.getIdentifier(it, "drawable", context.packageName)
            holder.imageView.setImageResource(resId)
        }

        // Εμφάνιση προσφοράς αν υπάρχει
        if (!item.offer.isNullOrBlank()) {
            holder.offerText.visibility = View.VISIBLE
            holder.offerText.text = item.offer
        } else {
            holder.offerText.visibility = View.GONE
        }

        // Κουμπί αύξησης
        holder.btnIncrease.setOnClickListener {
            onIncreaseClick(item)
        }
        // Κουμπί μείωσης
        holder.btnDecrease.setOnClickListener {
            onDecreaseClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<ShoppingCartItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    fun currentItems(): List<ShoppingCartItem> = items.toList()
}