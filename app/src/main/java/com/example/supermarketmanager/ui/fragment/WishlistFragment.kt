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

    // ViewModel with factory for dependency injection
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
    ) = try {
        // Inflate view with view binding and exception handling
        FragmentWishlistBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    } catch (e: Exception) {
        e.printStackTrace()
        super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            // Back button handler
            binding.backIcon.setOnClickListener {
                try {
                    findNavController().popBackStack()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // Set up RecyclerView with a 2-column grid layout
            binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

            // Observe wishlist LiveData and update adapter on data change
            vm.items.observe(viewLifecycleOwner) { list ->
                adapter = WishlistAdapter(
                    list,
                    onRemove = { item ->
                        try {
                            vm.removeItem(item.wishlist.id)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    onProductClick = { item ->
                        try {
                            // Open product detail with productId as argument
                            val bundle = Bundle().apply {
                                putInt("productId", item.product.id)
                            }
                            findNavController().navigate(
                                R.id.action_wishlistFragment_to_productDetailFragment,
                                bundle
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                )
                binding.rvWishlist.adapter = adapter
            }

            // Load initial data
            vm.loadItems()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
