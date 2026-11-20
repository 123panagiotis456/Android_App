package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.databinding.FragmentPurchaseHistoryBinding
import com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel

class PurchaseHistoryFragment : Fragment(R.layout.fragment_purchase_history) {

    private val historyViewModel: PurchaseHistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPurchaseHistoryBinding.bind(view)
        val adapter = PurchaseHistoryAdapter { purchase ->
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.purchaseHistoryFragment) {
                navController.navigate(
                    PurchaseHistoryFragmentDirections.actionPurchaseHistoryFragmentToPurchaseOrderFragment(purchase.id)
                )
            }
        }
        binding.recyclerViewPurchaseHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPurchaseHistory.adapter = adapter

        historyViewModel.history.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { purchases ->
                    adapter.updateData(purchases)
                    binding.recyclerViewPurchaseHistory.isVisible = purchases.isNotEmpty()
                    binding.tvHistoryStatus.setText(R.string.purchase_history_empty)
                    binding.tvHistoryStatus.isVisible = purchases.isEmpty()
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
}
