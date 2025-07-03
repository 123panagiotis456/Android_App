package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.models.ShoppingCartItem

class ShoppingCartAdapter(
    private val items: MutableList<ShoppingCartItem> = mutableListOf(),
    private val onAddClick: (ShoppingCartItem) -> Unit
) : RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder>() {


    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.tvName)
        val priceText: TextView = view.findViewById(R.id.tvPrice)
        val pricePerUnitText: TextView = view.findViewById(R.id.tvPricePerUnit)
        val imageView: ImageView = view.findViewById(R.id.ivImage)
        val offerText: TextView = view.findViewById(R.id.tvOffer)
        val addButton: ImageView = view.findViewById(R.id.ivAdd)
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
        holder.pricePerUnitText.text = "%.2f€/ %s".format(item.pricePerUnit, item.unit)

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

        // Κουμπί προσθήκης
        holder.addButton.setOnClickListener {
            onAddClick(item)
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
