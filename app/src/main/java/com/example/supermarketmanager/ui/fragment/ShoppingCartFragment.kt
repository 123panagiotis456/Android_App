package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.databinding.FragmentShoppingCartBinding
import com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import com.example.supermarketmanager.data.models.ShoppingCartItem

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    private val vm: ProductViewModel by viewModels()
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
            onAddClick = { item ->
                // Παράδειγμα λογικής όταν πατηθεί το "+"
                val newQuantity = item.quantity + 1
                val updatedItem = item.copy(quantity = newQuantity)

                val updatedList = adapter.currentItems().map {
                    if (it.id == item.id) updatedItem else it
                }

                adapter.updateData(updatedList)

                // Αν θέλεις να ενημερώνεται και η βάση:
                vm.updateCartItemQuantity(item.productId, newQuantity)
            }
        )


        binding.recyclerViewCart.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewCart.adapter = adapter

        vm.cartItems.observe(viewLifecycleOwner) { itemList ->
            adapter.updateData(itemList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
