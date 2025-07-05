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
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.launch
import android.util.Log

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    // ViewModel for product and shopping list operations
    private val vm: ProductViewModel by viewModels()
    private val cartVm: ShoppingListViewModel by viewModels()
    private lateinit var adapter: ShoppingCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
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
                } else {
                    //vm.removeItemFromCart(item.productId)
                }
            }
        )

        binding.recyclerViewCart.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewCart.adapter = adapter

        // Observe combined cart items
        cartVm.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
        }
        cartVm.loadCartItems()

        // Order button: save to history then clear cart
        binding.btnOrder.setOnClickListener {
            Log.d("ShoppingCartFragment", "Order button clicked")
            lifecycleScope.launch {
                cartVm.makePurchase()
                Log.d("ShoppingCartFragment", "makePurchase() completed")
                Toast.makeText(requireContext(), "Η παραγγελία καταχωρήθηκε!", Toast.LENGTH_SHORT).show()
                cartVm.loadCartItems()
                Log.d("ShoppingCartFragment", "loadCartItems() called after purchase")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
