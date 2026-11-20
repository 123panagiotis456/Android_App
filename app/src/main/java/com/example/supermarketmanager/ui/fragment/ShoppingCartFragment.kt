package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.data.repository.CartRepository
import com.example.supermarketmanager.databinding.FragmentShoppingCartBinding
import com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import android.util.Log

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    private val vm: ProductViewModel by viewModels()
    private val cartVm: ShoppingListViewModel by viewModels()
    private lateinit var adapter: ShoppingCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return try {
            _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
            binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Could not inflate ShoppingCartFragment layout", e)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingCartAdapter(
            mutableListOf(),
            onIncreaseClick = { item ->
                val newQuantity = item.quantity + 1
                vm.updateCartItemQuantity(item.productId, newQuantity)
            },
            onDecreaseClick = { item ->
                if (item.quantity > 1) {
                    val newQuantity = item.quantity - 1
                    vm.updateCartItemQuantity(item.productId, newQuantity)
                }
            }
        )

        binding.recyclerViewCart.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewCart.adapter = adapter

        cartVm.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
            if (items.isEmpty()) {
                binding.tvEmptyCart.visibility = View.VISIBLE
                binding.recyclerViewCart.visibility = View.GONE
                binding.btnOrder.visibility = View.GONE
                binding.btnDelete.visibility = View.GONE
            } else {
                binding.tvEmptyCart.visibility = View.GONE
                binding.recyclerViewCart.visibility = View.VISIBLE
                binding.btnOrder.visibility = View.VISIBLE
                binding.btnDelete.visibility = View.VISIBLE
            }
        }
        binding.btnOrder.setOnClickListener {
            binding.btnOrder.isEnabled = false
            binding.btnDelete.isEnabled = false
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val message = if (cartVm.makePurchase()) R.string.order_completed
                    else R.string.order_empty_cart
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                } catch (e: CartRepository.InsufficientStock) {
                    val message = getString(R.string.order_insufficient_stock, e.productName, e.available)
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    Log.e("ShoppingCartFragment", "Order failed", e)
                    Toast.makeText(requireContext(), R.string.order_failed, Toast.LENGTH_SHORT).show()
                } finally {
                    _binding?.let {
                        it.btnOrder.isEnabled = true
                        it.btnDelete.isEnabled = true
                    }
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    cartVm.clearCart()
                    Toast.makeText(requireContext(), "Το καλάθι διαγράφηκε", Toast.LENGTH_SHORT).show()
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    Log.e("ShoppingCartFragment", "Cart clear failed", e)
                    Toast.makeText(requireContext(), "Αποτυχία διαγραφής καλαθιού.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
