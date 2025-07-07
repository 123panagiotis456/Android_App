package com.example.supermarketmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.databinding.ItemProductBinding
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductAdapter(
    private val viewModel: ProductViewModel,
    private val onClick: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<ProductEntity> = emptyList()
    private val quantityMap = mutableMapOf<Int, Int>() // τοπική παρακολούθηση ποσοτήτων

    fun submitList(list: List<ProductEntity>) {
        products = list
        notifyDataSetChanged()
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        quantityMap[productId] = quantity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductEntity) {
            // Όνομα, περιγραφή, τιμή
            binding.tvProductName.text = product.name
            binding.tvProductDescription.text = product.description
            binding.tvProductPrice.text = "${product.pricePerUnit} ${product.unit}"

            // Εικόνα
            val context = binding.ivProductImage.context
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

            // Ποσότητα για το συγκεκριμένο προϊόν
            val quantity = quantityMap[product.id] ?: 0

            // Εμφάνιση ανάλογα με ποσότητα
            if (quantity == 0) {
                binding.btnAdd.visibility = View.VISIBLE
                binding.quantityLayout.visibility = View.GONE
            } else {
                binding.btnAdd.visibility = View.GONE
                binding.quantityLayout.visibility = View.VISIBLE
                binding.tvQuantity.text = quantity.toString()
            }

            // Disable το κουμπί αύξησης αν έχουμε φτάσει το stock
            binding.btnIncrease.isEnabled = quantity < (product.availability)

            // Πάτημα στο αρχικό '+' κουμπί
            binding.btnAdd.setOnClickListener {
                if (product.availability > 0) {
                    viewModel.addOneToCart(product.id)
                    quantityMap[product.id] = 1
                    notifyItemChanged(adapterPosition)
                }
            }

            // Πάτημα στο ➕
            binding.btnIncrease.setOnClickListener {
                val current = quantityMap[product.id] ?: 1
                if (current < product.availability) {
                    viewModel.addOneToCart(product.id)
                    quantityMap[product.id] = current + 1
                    binding.tvQuantity.text = (current + 1).toString()
                }
                // Disable αν φτάσαμε το μέγιστο
                binding.btnIncrease.isEnabled = ((quantityMap[product.id] ?: 0) < product.availability)
            }

            // Πάτημα στο ➖
            binding.btnDecrease.setOnClickListener {
                val current = quantityMap[product.id] ?: 1
                viewModel.removeOneFromCart(product.id)
                if (current > 1) {
                    quantityMap[product.id] = current - 1
                    binding.tvQuantity.text = (current - 1).toString()
                } else {
                    quantityMap[product.id] = 0
                    notifyItemChanged(adapterPosition)
                }
                // Ενεργοποιούμε πάλι το increase αν πέσαμε κάτω από το διαθέσιμο
                binding.btnIncrease.isEnabled = ((quantityMap[product.id] ?: 0) < product.availability)
            }
        }
    }
}
