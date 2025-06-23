package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.supermarketmanager.databinding.FragmentProductListBinding
import com.example.supermarketmanager.ui.adapter.ProductAdapter
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val vm: ProductViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentProductListBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryId = requireArguments().getInt("categoryId")
        vm.products.observe(viewLifecycleOwner) { list ->
            binding.rvProducts.adapter = ProductAdapter(list) { product ->
                findNavController().navigate(
                    com.example.supermarketmanager.R.id.productDetailFragment,
                    Bundle().apply { putInt("productId", product.id) }
                )
            }
        }
        vm.loadByCategory(categoryId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
