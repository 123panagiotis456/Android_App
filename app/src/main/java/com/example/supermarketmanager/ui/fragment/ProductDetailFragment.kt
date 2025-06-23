package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.supermarketmanager.databinding.FragmentProductDetailBinding
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val vm: ProductViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentProductDetailBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ProductDetailFragment", "productId: ${args.productId}")  // <-- Εδώ

        vm.product.observe(viewLifecycleOwner) { p ->
            Log.d("ProductDetailFragment", "Loaded product: $p")        // <-- Και εδώ
            binding.tvName.text = p.name
            binding.tvDesc.text = p.description
            binding.tvPrice.text = "${p.pricePerUnit}€/${p.unit}"
            Glide.with(binding.ivProduct).load(p.imageUrl).into(binding.ivProduct)
        }

        vm.loadById(args.productId)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
