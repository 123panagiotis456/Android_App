package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
import com.google.android.material.snackbar.Snackbar
import com.example.supermarketmanager.databinding.FragmentPurchaseOrderBinding
import com.example.supermarketmanager.databinding.ItemPurchaseOrderProductBinding
import com.example.supermarketmanager.ui.formatEuro
import com.example.supermarketmanager.ui.formatPurchaseDate
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel.OrderLine
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class PurchaseOrderFragment : Fragment(R.layout.fragment_purchase_order) {

    private val args: PurchaseOrderFragmentArgs by navArgs()
    private val historyViewModel: PurchaseHistoryViewModel by viewModels()
    private val cartViewModel: ShoppingListViewModel by viewModels()
    private var cartMessage: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPurchaseOrderBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.orderProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.openCart.setOnClickListener { openCart() }

        val adapter = OrderLineAdapter { productId, name -> addProduct(binding, productId, name) }
        binding.orderProducts.adapter = adapter
        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            val quantities = items.groupingBy { it.productId }.fold(0) { count, item ->
                count + item.quantity
            }
            adapter.updateCartQuantities(quantities)
            val unitCount = quantities.values.sum()
            binding.cartUnitCount.text = resources.getQuantityString(
                R.plurals.purchase_order_cart_units, unitCount, unitCount
            )
            binding.cartCurrentTotal.text = requireContext().formatEuro(
                items.sumOf { it.pricePerUnit * it.quantity }
            )
            binding.cartFooter.isVisible = true
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val order = historyViewModel.loadOrder(args.purchaseId)
                if (order == null) {
                    binding.orderStatus.setText(R.string.purchase_order_missing)
                    binding.orderStatus.isVisible = true
                    return@launch
                }
                binding.orderDate.text = requireContext().formatPurchaseDate(order.purchase.timestamp)
                binding.orderTotal.text = requireContext().formatEuro(order.purchase.totalCost)
                binding.orderSummary.isVisible = true
                binding.orderProductsHeading.isVisible = order.lines.isNotEmpty()
                adapter.showLines(order.lines)
                binding.orderStatus.setText(R.string.purchase_history_legacy)
                binding.orderStatus.isVisible = order.lines.isEmpty()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseOrderFragment", "Error loading order", e)
                binding.orderStatus.setText(R.string.purchase_history_load_failed)
                binding.orderStatus.isVisible = true
            } finally {
                binding.orderProgress.isVisible = false
            }
        }
    }

    private fun openCart() {
        val navController = findNavController()
        if (navController.currentDestination?.id == R.id.purchaseOrderFragment) {
            navController.navigate(
                PurchaseOrderFragmentDirections.actionPurchaseOrderFragmentToShoppingCartFragment()
            )
        }
    }

    private fun addProduct(binding: FragmentPurchaseOrderBinding, productId: Int, name: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            val message = try {
                if (cartViewModel.addOneIfAvailable(productId)) {
                    getString(R.string.purchase_order_added_named, name)
                } else {
                    getString(R.string.purchase_order_stock)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseOrderFragment", "Error adding product", e)
                getString(R.string.cart_update_failed)
            }
            cartMessage?.dismiss()
            cartMessage = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.cartFooter)
                .also { it.show() }
        }
    }

    override fun onDestroyView() {
        cartMessage?.dismiss()
        cartMessage = null
        super.onDestroyView()
    }

    private class OrderLineAdapter(
        private val onAdd: (Int, String) -> Unit
    ) : RecyclerView.Adapter<OrderLineAdapter.Holder>() {

        private var lines: List<OrderLine> = emptyList()
        private var cartQuantities: Map<Int, Int> = emptyMap()

        fun showLines(newLines: List<OrderLine>) {
            lines = newLines
            notifyDataSetChanged()
        }

        fun updateCartQuantities(quantities: Map<Int, Int>) {
            val previous = cartQuantities
            cartQuantities = quantities
            lines.forEachIndexed { index, line ->
                if (previous[line.productId] != quantities[line.productId]) {
                    notifyItemChanged(index, CART_QUANTITY_CHANGED)
                }
            }
        }

        class Holder(val binding: ItemPurchaseOrderProductBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(ItemPurchaseOrderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val line = lines[position]
            val binding = holder.binding
            val context = binding.root.context
            val name = line.name ?: context.getString(R.string.purchase_history_product, line.productId)
            binding.productName.text = name
            binding.productPurchase.text =
                context.getString(R.string.purchase_order_quantity, line.quantity)
            binding.productPrice.text =
                context.getString(R.string.purchase_order_unit_price, context.formatEuro(line.unitPrice))
            val imageId = line.imageDrawable?.let {
                context.resources.getIdentifier(it, "drawable", context.packageName)
            } ?: 0
            binding.productImage.setImageResource(if (imageId != 0) imageId else R.drawable.ic_placeholder)
            bindCartState(binding, line)
            binding.addProduct.contentDescription = context.getString(R.string.purchase_order_add, name)
            binding.addProduct.setOnClickListener { onAdd(line.productId, name) }
        }

        override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
            if (payloads.contains(CART_QUANTITY_CHANGED)) {
                bindCartState(holder.binding, lines[position])
            } else {
                super.onBindViewHolder(holder, position, payloads)
            }
        }

        private fun bindCartState(binding: ItemPurchaseOrderProductBinding, line: OrderLine) {
            val quantity = cartQuantities[line.productId] ?: 0
            val canAdd = quantity < line.availability
            binding.productCartQuantity.text = binding.root.context.getString(
                R.string.purchase_order_in_cart, quantity
            )
            binding.productCartQuantity.isVisible = quantity > 0
            binding.productUnavailable.setText(
                if (line.availability <= 0) R.string.purchase_order_unavailable
                else R.string.purchase_order_stock_limit
            )
            binding.productUnavailable.isVisible = !canAdd
            binding.addProduct.isEnabled = canAdd
        }

        override fun getItemCount(): Int = lines.size

        private companion object {
            const val CART_QUANTITY_CHANGED = "cart_quantity_changed"
        }
    }
}
