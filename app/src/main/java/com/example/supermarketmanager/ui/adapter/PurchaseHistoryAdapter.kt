package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class PurchaseHistoryAdapter(
    private val items: List<PurchaseHistoryEntity>
) : RecyclerView.Adapter<PurchaseHistoryAdapter.VH>() {

    inner class VH(val binding: ItemPurchaseHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemPurchaseHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        holder.binding.tvDate.text = sdf.format(Date(e.timestamp))
        holder.binding.tvTotal.text = "${e.totalCost}€"
    }

    override fun getItemCount() = items.size
}
