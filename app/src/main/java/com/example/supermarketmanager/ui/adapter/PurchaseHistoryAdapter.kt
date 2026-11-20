package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding
import com.example.supermarketmanager.ui.formatEuro
import com.example.supermarketmanager.ui.formatPurchaseDate

class PurchaseHistoryAdapter(
    private val onOrderClick: (PurchaseHistoryEntity) -> Unit
) : RecyclerView.Adapter<PurchaseHistoryAdapter.PurchaseViewHolder>() {

    private var purchases: List<PurchaseHistoryEntity> = emptyList()

    class PurchaseViewHolder(val binding: ItemPurchaseHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder =
        PurchaseViewHolder(
            ItemPurchaseHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]
        val binding = holder.binding
        val context = binding.root.context
        binding.tvPurchaseDate.text = context.formatPurchaseDate(purchase.timestamp)
        binding.tvTotalCost.text = context.formatEuro(purchase.totalCost)
        binding.root.setOnClickListener { onOrderClick(purchase) }
    }

    override fun getItemCount(): Int = purchases.size

    fun updateData(newPurchases: List<PurchaseHistoryEntity>) {
        purchases = newPurchases
        notifyDataSetChanged()
    }
}
