package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supermarketmanager.R
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.databinding.FragmentProductDetailBinding
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val vm: ProductViewModel by viewModels()

    private var quantity = 1
    private var isFavorite = false
    private var maxAvailability = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductDetailBinding.bind(view)

        val context = requireContext()
        val wishlistDao = AppDatabase.getInstance(context).wishlistDao()
        val productId = arguments?.getInt("productId") ?: -1

        // Set up the toolbar back arrow
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        // Check if the product is in the favorites list
        lifecycleScope.launch {
            try {
                isFavorite = wishlistDao.getItemByProductId(productId) != null
                updateFavoriteIcon()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load favorite status", e)
            }
        }

        binding.btnFavorite.setOnClickListener {
            lifecycleScope.launch {
                try {
                    if (isFavorite) {
                        val item = wishlistDao.getItemByProductId(productId)
                        item?.let { wishlistDao.removeItem(it.id) }
                    } else {
                        wishlistDao.addItem(WishlistItemEntity(productId = productId))
                    }
                    isFavorite = !isFavorite
                    updateFavoriteIcon()
                } catch (e: Exception) {
                    Log.e(TAG, "Error updating favorite", e)
                    Toast.makeText(context, "Error updating favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Observe cart items to update the current quantity and the + button
        vm.cartItems.observe(viewLifecycleOwner) { cart ->
            val item = cart.find { it.productId == productId }
            quantity = item?.quantity ?: 1
            binding.tvDetailQuantity.text = quantity.toString()
            binding.btnIncreaseDetail.isEnabled = (quantity < maxAvailability)
        }

        // Observe the product details and update UI accordingly
        vm.getById(productId).observe(viewLifecycleOwner) { product ->
            if (product == null) {
                try {
                    findNavController().popBackStack()
                } catch (e: IllegalStateException) {
                    Log.e(TAG, "Navigation back from missing product failed", e)
                    Toast.makeText(context, "Unable to navigate back", Toast.LENGTH_SHORT).show()
                }
                return@observe
            }
            maxAvailability = product.availability

            val resId = context.resources.getIdentifier(product.imageDrawable, "drawable", context.packageName)
            binding.ivDetailImage.setImageResource(
                if (resId != 0) resId else R.drawable.ic_placeholder,
            )

            binding.tvDetailnutritionalInfo.text = product.nutritionalInfo
            binding.tvDetailingredients.text = product.ingredients
            binding.tvDetailName.text = product.name
            binding.tvDetailDescription.text = product.description ?: ""
            binding.tvDetailPrice.text = "${product.pricePerUnit} €"
            binding.tvDetailUnitInfo.text = "Τιμή ανά ${product.unit}"

            // Display product availability
            binding.tvDetailAvailability.text = "${product.availability}"

            // Show offer badge if exists
            if (!product.offer.isNullOrBlank()) {
                binding.tvDetailOffer.visibility = View.VISIBLE
                binding.tvDetailOffer.text = product.offer
            } else {
                binding.tvDetailOffer.visibility = View.GONE
            }

            binding.btnIncreaseDetail.isEnabled = (quantity < product.availability)
        }

        // Increase quantity button logic with availability check
        binding.btnIncreaseDetail.setOnClickListener {
            if (quantity < maxAvailability) {
                quantity++
                binding.tvDetailQuantity.text = quantity.toString()
            }
            binding.btnIncreaseDetail.isEnabled = (quantity < maxAvailability)
        }

        // Decrease quantity button logic
        binding.btnDecreaseDetail.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvDetailQuantity.text = quantity.toString()
                binding.btnIncreaseDetail.isEnabled = (quantity < maxAvailability)
            }
        }

        // Add to cart button, update the cart quantity
        binding.btnAddDetail.setOnClickListener {
            try {
                vm.setCartQuantity(productId, quantity)
                findNavController().popBackStack()
            } catch (e: Exception) {
                Log.e(TAG, "Error adding to cart", e)
                Toast.makeText(context, "Error adding to cart", Toast.LENGTH_SHORT).show()
            }
        }

        // Back arrow navigation from toolbar
        binding.toolbar.setNavigationOnClickListener {
            try {
                findNavController().popBackStack()
            } catch (e: IllegalStateException) {
                Log.e(TAG, "Back navigation failed", e)
                Toast.makeText(context, "Unable to navigate back", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Updates the favorite button icon depending on favorite status.
     */
    private fun updateFavoriteIcon() {
        val iconRes = if (isFavorite) {
            R.drawable.ic_favorite // filled heart
        } else {
            R.drawable.ic_favorite_border // empty heart
        }
        binding.btnFavorite.setImageResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }

    companion object {
        private const val TAG = "ProductDetailFragment"
    }
}
