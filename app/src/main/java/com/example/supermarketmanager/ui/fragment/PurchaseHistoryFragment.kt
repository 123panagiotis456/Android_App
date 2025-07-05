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
    private val cartVM:     ShoppingListViewModel    by viewModels()
    private lateinit var adapter: PurchaseHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PurchaseHistoryAdapter(
            emptyList(), emptyMap()
        ) { purchase ->
            cartVM.readdPurchaseToCart(purchase)
            Toast.makeText(requireContext(),
                "Τα προϊόντα προστέθηκαν ξανά στο καλάθι", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewPurchaseHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPurchaseHistory.adapter = adapter

        historyVM.purchaseHistory.observe(viewLifecycleOwner) { purchases ->
            lifecycleScope.launch {
                val ids = purchases.flatMap { it.productIds }.toSet().toList()
                val map = MyApplication.database.productDao()
                    .getByIdList(ids)
                    .associate { it.id to it.name }

                adapter.updateData(purchases, map)
            }
        }
        historyVM.loadPurchaseHistory()

        // bottom‑nav buttons
        binding.btnCart.setOnClickListener { findNavController().navigate(R.id.shoppingCartFragment) }
        binding.btnWishlist.setOnClickListener { findNavController().navigate(R.id.wishlistFragment) }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
