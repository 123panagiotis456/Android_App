package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.databinding.FragmentPurchaseHistoryBinding
import com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.supermarketmanager.R
import android.widget.Toast
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel

class PurchaseHistoryFragment : Fragment() {

    private var _binding: FragmentPurchaseHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyVM: PurchaseHistoryViewModel by viewModels()
    private val cartVM: ShoppingListViewModel by viewModels()
    private lateinit var adapter: PurchaseHistoryAdapter

    // Create the view for the fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return try {
            _binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false)
            binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Set up logic and UI after the view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // Setup the adapter with click callback for "buy again"
            adapter = PurchaseHistoryAdapter(
                emptyList(), emptyMap()
            ) { purchase ->
                try {
                    cartVM.readdPurchaseToCart(purchase)
                    Toast.makeText(
                        requireContext(),
                        "Τα προϊόντα προστέθηκαν ξανά στο καλάθι",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // Set RecyclerView layout manager and adapter
            binding.recyclerViewPurchaseHistory.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewPurchaseHistory.adapter = adapter

            // Observe the purchase history and update adapter data
            historyVM.purchaseHistory.observe(viewLifecycleOwner) { purchases ->
                lifecycleScope.launch {
                    try {
                        val ids = purchases.flatMap { it.productIds }.toSet().toList()
                        val map = MyApplication.database.productDao()
                            .getByIdList(ids)
                            .associate { it.id to it.name }

                        adapter.updateData(purchases, map)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            // Load initial history
            historyVM.loadPurchaseHistory()

            // Handle navigation for bottom navigation buttons
            binding.btnCart.setOnClickListener {
                try {
                    findNavController().navigate(R.id.shoppingCartFragment)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            binding.btnWishlist.setOnClickListener {
                try {
                    findNavController().navigate(R.id.wishlistFragment)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
