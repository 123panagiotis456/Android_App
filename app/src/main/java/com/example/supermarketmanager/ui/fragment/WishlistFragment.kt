package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.repository.WishlistRepository
import com.example.supermarketmanager.databinding.FragmentWishlistBinding
import com.example.supermarketmanager.ui.adapter.WishlistAdapter
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModel
import com.example.supermarketmanager.ui.viewmodel.WishlistViewModelFactory

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWishlistBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.items.observe(viewLifecycleOwner) { list ->
            binding.rvWishlist.adapter = WishlistAdapter(list) { item ->
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
