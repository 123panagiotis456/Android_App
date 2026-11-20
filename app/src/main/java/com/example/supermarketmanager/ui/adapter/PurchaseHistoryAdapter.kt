package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding
import com.example.supermarketmanager.ui.formatEuro
import java.text.DateFormat
import java.util.Date

class PurchaseHistoryAdapter(
    private val onReaddClick: (PurchaseHistoryEntity) -> Unit
) : RecyclerView.Adapter<PurchaseHistoryAdapter.PurchaseViewHolder>() {

    private var purchases: List<PurchaseHistoryEntity> = emptyList()
    private var productNames: Map<Int, String> = emptyMap()

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
        val dateFormat = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM, DateFormat.SHORT, context.resources.configuration.locales[0]
        )
        binding.tvPurchaseDate.text = dateFormat.format(Date(purchase.timestamp))
        binding.tvTotalCost.text = context.formatEuro(purchase.totalCost)

        val hasDetails = purchase.productIds.isNotEmpty() &&
            purchase.productIds.size == purchase.quantities.size &&
            purchase.productIds.size == purchase.prices.size
        binding.tvProductsDetails.text = if (hasDetails) {
            purchase.productIds.indices.joinToString("\n") { index ->
                val productId = purchase.productIds[index]
                val name = productNames[productId]
                    ?: context.getString(R.string.purchase_history_product, productId)
                context.getString(
                    R.string.purchase_history_line,
                    purchase.quantities[index],
                    name,
                    context.formatEuro(purchase.prices[index])
                )
            }
        } else {
            context.getString(R.string.purchase_history_legacy)
        }

        binding.btnReaddToCart.isVisible = hasDetails
        binding.btnReaddToCart.setOnClickListener { onReaddClick(purchase) }
    }

    override fun getItemCount(): Int = purchases.size

    fun updateData(newPurchases: List<PurchaseHistoryEntity>, newProductNames: Map<Int, String>) {
        purchases = newPurchases
        productNames = newProductNames
        notifyDataSetChanged()
    }
}
