package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.databinding.FragmentWishlistBinding
import com.example.supermarketmanager.ui.adapter.WishlistAdapter
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModel
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModelFactory

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {

    private val vm: WishlistViewModel by viewModels { WishlistViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWishlistBinding.bind(view)
        val adapter = WishlistAdapter(
            onRemove = { vm.removeItem(it.wishlist.id) },
            onProductClick = {
                findNavController().navigate(
                    WishlistFragmentDirections.actionWishlistFragmentToProductDetailFragment(it.product.id)
                )
            }
        )
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWishlist.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        vm.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.rvWishlist.isVisible = items.isNotEmpty()
            binding.tvWishlistEmpty.isVisible = items.isEmpty()
        }
        vm.loadItems()
    }
}
