package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.repository.WishlistRepository
import com.example.supermarketmanager.databinding.FragmentWishlistBinding
import com.example.supermarketmanager.ui.adapter.WishlistAdapter
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModel
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModelFactory
import com.example.supermarketmanager.R

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private val vm: WishlistViewModel by viewModels {
        WishlistViewModelFactory(
            WishlistRepository(
                AppDatabase.getInstance(requireContext()).wishlistDao()
            )
        )
    }

    private lateinit var adapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWishlistBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

        vm.items.observe(viewLifecycleOwner) { list ->
            adapter = WishlistAdapter(
                list,
                onRemove = { item -> vm.removeItem(item.wishlist.id) },
                onProductClick = { item ->
                    // Απλός παραδοσιακός τρόπος με Bundle
                    val bundle = Bundle().apply {
                        putInt("productId", item.product.id)
                    }
                    findNavController().navigate(
                        R.id.action_wishlistFragment_to_productDetailFragment,
                        bundle
                    )
                }
            )
            binding.rvWishlist.adapter = adapter
        }

        vm.loadItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
