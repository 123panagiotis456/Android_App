package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import java.text.SimpleDateFormat
import java.util.*

class PurchaseHistoryAdapter(
    private var purchases: List<PurchaseHistoryEntity>,
    private var productIdToName: Map<Int, String>,
    private val onReaddClick: (PurchaseHistoryEntity) -> Unit
) : RecyclerView.Adapter<PurchaseHistoryAdapter.PurchaseViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())

    inner class PurchaseViewHolder(val binding: ItemPurchaseHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding = ItemPurchaseHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]

        holder.binding.tvPurchaseDate.text = dateFormat.format(Date(purchase.timestamp))
        holder.binding.tvTotalCost.text    = "Σύνολο: €%.2f".format(purchase.totalCost)

        holder.binding.tvProductsDetails.text =
            purchase.productIds.indices.joinToString("\n") { i ->
                val name = productIdToName[purchase.productIds[i]] ?: "Unknown"
                "• $name x${purchase.quantities[i]} @ €${purchase.prices[i]}"
            }

        holder.binding.btnReaddToCart.setOnClickListener { onReaddClick(purchase) }
    }

    override fun getItemCount(): Int = purchases.size

    fun updateData(newPurchases: List<PurchaseHistoryEntity>,
                   newProductIdToName: Map<Int, String>) {
        purchases = newPurchases
        productIdToName = newProductIdToName
        notifyDataSetChanged()
    }
}
