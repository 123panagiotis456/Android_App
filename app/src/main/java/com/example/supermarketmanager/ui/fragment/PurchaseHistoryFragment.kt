package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.databinding.FragmentPurchaseHistoryBinding
import com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class PurchaseHistoryFragment : Fragment(R.layout.fragment_purchase_history) {

    private val historyViewModel: PurchaseHistoryViewModel by viewModels()
    private val cartViewModel: ShoppingListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPurchaseHistoryBinding.bind(view)
        val adapter = PurchaseHistoryAdapter(::readdPurchase)
        binding.recyclerViewPurchaseHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPurchaseHistory.adapter = adapter

        historyViewModel.history.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { history ->
                    adapter.updateData(history.purchases, history.productNames)
                    binding.recyclerViewPurchaseHistory.isVisible = history.purchases.isNotEmpty()
                    binding.tvHistoryStatus.isVisible = history.purchases.isEmpty()
                    binding.tvHistoryStatus.setText(R.string.purchase_history_empty)
                },
                onFailure = {
                    binding.recyclerViewPurchaseHistory.isVisible = false
                    binding.tvHistoryStatus.setText(R.string.purchase_history_load_failed)
                    binding.tvHistoryStatus.isVisible = true
                }
            )
        }
        historyViewModel.loadPurchaseHistory()
    }

    private fun readdPurchase(purchase: PurchaseHistoryEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                cartViewModel.readdPurchaseToCart(purchase)
                showMessage(R.string.purchase_readded)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseHistoryFragment", "Error readding purchase to cart", e)
                showMessage(R.string.cart_update_failed)
            }
        }
    }

    private fun showMessage(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
