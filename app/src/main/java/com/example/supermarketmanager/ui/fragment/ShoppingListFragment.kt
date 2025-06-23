package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.supermarketmanager.databinding.FragmentShoppingListBinding
import com.example.supermarketmanager.ui.adapter.ShoppingListAdapter
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel

class ShoppingListFragment : Fragment() {
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private val vm: ShoppingListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentShoppingListBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.items.observe(viewLifecycleOwner) { list ->
            binding.rvShoppingList.adapter = ShoppingListAdapter(list) { item ->
                vm.removeItem(item.id)
            }
        }
        vm.loadItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
