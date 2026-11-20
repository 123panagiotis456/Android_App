package com.example.supermarketmanager.ui.adapter

import android.animation.ValueAnimator
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.databinding.ItemProductBinding
import com.example.supermarketmanager.ui.formatEuro
import com.example.supermarketmanager.ui.priceUnitLabel
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductAdapter(
    private val viewModel: ProductViewModel,
    private val onClick: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<ProductEntity> = emptyList()
    private var quantityMap: Map<Int, Int> = emptyMap()

    fun submitList(list: List<ProductEntity>) {
        products = list
        notifyDataSetChanged()
    }

    fun updateQuantities(quantities: Map<Int, Int>) {
        if (quantityMap == quantities) return
        val previous = quantityMap
        quantityMap = quantities
        products.forEachIndexed { index, product ->
            val before = previous[product.id] ?: 0
            val after = quantities[product.id] ?: 0
            if (before != after) {
                notifyItemChanged(index, (before == 0) != (after == 0))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val product = products[position]
            holder.renderQuantity(product, quantityMap[product.id] ?: 0, payloads.any { it == true })
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductEntity) {
            // Όνομα, περιγραφή, τιμή
            binding.tvProductName.text = product.name
            binding.tvProductDescription.text = product.description
            val context = binding.root.context
            binding.tvProductPrice.text = context.formatEuro(product.pricePerUnit)
            binding.tvProductUnit.text = context.priceUnitLabel(product.unit)

            // Εικόνα
            val resId = product.imageDrawable?.let {
                context.resources.getIdentifier(it, "drawable", context.packageName)
            } ?: 0
            binding.ivProductImage.setImageResource(
                if (resId != 0) resId else android.R.drawable.ic_menu_report_image
            )

            // Προσφορά
            if (!product.offer.isNullOrBlank()) {
                binding.tvProductOffer.visibility = View.VISIBLE
                binding.tvProductOffer.text = product.offer
            } else {
                binding.tvProductOffer.visibility = View.GONE
            }

            // Click σε όλο το item
            itemView.setOnClickListener {
                onClick(product)
            }

            renderQuantity(product, quantityMap[product.id] ?: 0)

            binding.btnAdd.setOnClickListener {
                if (product.availability > 0) {
                    viewModel.addOneToCart(product.id)
                }
            }

            binding.btnIncrease.setOnClickListener {
                val current = quantityMap[product.id] ?: 0
                if (current >= product.availability) return@setOnClickListener
                viewModel.addOneToCart(product.id)
            }

            binding.btnDecrease.setOnClickListener {
                val current = quantityMap[product.id] ?: 0
                if (current == 0) return@setOnClickListener
                viewModel.removeOneFromCart(product.id)
            }
        }

        fun renderQuantity(product: ProductEntity, quantity: Int, animateControls: Boolean = false) {
            binding.btnAdd.isVisible = quantity == 0
            binding.quantityLayout.isVisible = quantity > 0
            binding.btnAdd.isEnabled = product.availability > 0
            binding.btnIncrease.isEnabled = quantity < product.availability
            binding.tvQuantity.text = quantity.toString()
            binding.tvQuantity.contentDescription = binding.root.context.getString(
                R.string.nav_cart_quantity, binding.tvQuantity.text
            )

            binding.btnAdd.animate().cancel()
            binding.quantityLayout.animate().cancel()
            binding.btnAdd.alpha = 1f
            binding.quantityLayout.alpha = 1f
            if (animateControls && (Build.VERSION.SDK_INT < 26 || ValueAnimator.areAnimatorsEnabled())) {
                val controls = if (quantity > 0) binding.quantityLayout else binding.btnAdd
                controls.alpha = 0.55f
                controls.animate().alpha(1f).setDuration(150).start()
            }
        }
    }
}
