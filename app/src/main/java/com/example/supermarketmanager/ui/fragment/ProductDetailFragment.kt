package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.supermarketmanager.R
import com.example.supermarketmanager.databinding.FragmentProductDetailBinding
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ProductDetailFragmentArgs by navArgs()
    private val vm: ProductViewModel by viewModels()

    private var quantity = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)

        // Back arrow
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        // Παρακολούθηση καλαθιού για να αρχικοποιηθεί η ποσότητα
        vm.cartItems.observe(viewLifecycleOwner) { cart ->
            val item = cart.find { it.productId == args.productId }
            quantity = item?.quantity ?: 1
            binding.tvDetailQuantity.text = quantity.toString()
        }

        // Πλήκτρα + και -
        binding.btnIncreaseDetail.setOnClickListener {
            quantity++
            binding.tvDetailQuantity.text = quantity.toString()
        }

        binding.btnDecreaseDetail.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvDetailQuantity.text = quantity.toString()
            }
        }

        // Παρακολούθηση προϊόντος
        vm.getById(args.productId).observe(viewLifecycleOwner) { product ->
            if (product == null) {
                findNavController().popBackStack()
                return@observe
            }

            // Εικόνα
            val resId = requireContext()
                .resources
                .getIdentifier(product.imageDrawable, "drawable", requireContext().packageName)
            binding.ivDetailImage.setImageResource(
                if (resId != 0) resId else R.drawable.ic_placeholder
            )

            // Πεδία
            binding.tvDetailnutritionalInfo.text = product.nutritionalInfo
            binding.tvDetailingredients.text = product.ingredients
            binding.tvDetailName.text = product.name
            binding.tvDetailDescription.text = product.description ?: ""
            binding.tvDetailPrice.text = "${product.pricePerUnit} €"
            binding.tvDetailUnitInfo.text = "Τιμή ανά ${product.unit}"
            binding.tvDetailSubtitle.text = product.description

            if (!product.offer.isNullOrBlank()) {
                binding.tvDetailOffer.visibility = View.VISIBLE
                binding.tvDetailOffer.text = product.offer
            } else {
                binding.tvDetailOffer.visibility = View.GONE
            }

            // Κουμπί "Προσθήκη"
            binding.btnAddDetail.setOnClickListener {
                vm.setCartQuantity(product.id, quantity)
                findNavController().popBackStack()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
