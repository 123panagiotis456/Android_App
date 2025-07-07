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
import com.example.supermarketmanager.databinding.FragmentShoppingCartBinding
import com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel
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
        cartVm.loadCartItems()

        binding.btnOrder.setOnClickListener {
            Log.d("ShoppingCartFragment", "Order button clicked")
            lifecycleScope.launch {
                try {
                    cartVm.makePurchase()
                    Toast.makeText(requireContext(), "Η παραγγελία καταχωρήθηκε!", Toast.LENGTH_SHORT).show()
                    cartVm.loadCartItems()
                    Log.d("ShoppingCartFragment", "Order completed & cart reloaded")
                } catch (e: Exception) {
                    Log.e("ShoppingCartFragment", "Order failed", e)
                    Toast.makeText(requireContext(), "Παρουσιάστηκε σφάλμα κατά την παραγγελία.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            lifecycleScope.launch {
                try {
                    cartVm.clearCart()
                    Toast.makeText(requireContext(), "Το καλάθι διαγράφηκε", Toast.LENGTH_SHORT).show()
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
