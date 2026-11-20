package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketmanager.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPurchaseOrderBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.orderProducts.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val order = historyViewModel.loadOrder(args.purchaseId)
                if (order == null) {
                    binding.orderStatus.setText(R.string.purchase_order_missing)
                    binding.orderStatus.isVisible = true
                    return@launch
                }
                binding.orderDate.text = requireContext().formatPurchaseDate(order.purchase.timestamp)
                binding.orderTotal.text = getString(
                    R.string.purchase_order_total, requireContext().formatEuro(order.purchase.totalCost)
                )
                binding.orderSummary.isVisible = true
                binding.orderProducts.adapter = OrderLineAdapter(order.lines, ::addProduct)
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

    private fun addProduct(productId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val message = try {
                if (cartViewModel.addOneIfAvailable(productId)) R.string.purchase_order_added
                else R.string.purchase_order_stock
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseOrderFragment", "Error adding product", e)
                R.string.cart_update_failed
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private class OrderLineAdapter(
        private val lines: List<OrderLine>,
        private val onAdd: (Int) -> Unit
    ) : RecyclerView.Adapter<OrderLineAdapter.Holder>() {

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
            binding.productPurchase.text = context.getString(
                R.string.purchase_order_line, line.quantity, context.formatEuro(line.unitPrice)
            )
            binding.productUnavailable.isVisible = !line.inStock
            binding.addProduct.isEnabled = line.inStock
            binding.addProduct.contentDescription = context.getString(R.string.purchase_order_add, name)
            binding.addProduct.setOnClickListener { onAdd(line.productId) }
        }

        override fun getItemCount(): Int = lines.size
    }
}
