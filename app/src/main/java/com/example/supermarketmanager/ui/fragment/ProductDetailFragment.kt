package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.supermarketmanager.R
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.databinding.FragmentProductDetailBinding
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ProductDetailFragmentArgs by navArgs()
    private val vm: ProductViewModel by viewModels()

    private var quantity = 1
    private var isFavorite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)

        val context = requireContext()
        val wishlistDao = AppDatabase.getInstance(context).wishlistDao()
        val productId = args.productId

        // Back arrow toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        // Ελέγχει αν είναι στα αγαπημένα
        lifecycleScope.launch {
            isFavorite = wishlistDao.getItemByProductId(productId) != null
            updateFavoriteIcon()
        }

        binding.btnFavorite.setOnClickListener {
            lifecycleScope.launch {
                if (isFavorite) {
                    val item = wishlistDao.getItemByProductId(productId)
                    item?.let { wishlistDao.removeItem(it.id) }
                } else {
                    wishlistDao.addItem(WishlistItemEntity(productId = productId))
                }
                isFavorite = !isFavorite
                updateFavoriteIcon()
            }
        }

        // Παρακολούθηση καλαθιού
        vm.cartItems.observe(viewLifecycleOwner) { cart ->
            val item = cart.find { it.productId == productId }
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
        vm.getById(productId).observe(viewLifecycleOwner) { product ->
            if (product == null) {
                findNavController().popBackStack()
                return@observe
            }

            val resId = context.resources.getIdentifier(product.imageDrawable, "drawable", context.packageName)
            binding.ivDetailImage.setImageResource(
                if (resId != 0) resId else R.drawable.ic_placeholder
            )

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

            binding.btnAddDetail.setOnClickListener {
                vm.setCartQuantity(product.id, quantity)
                findNavController().popBackStack()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateFavoriteIcon() {
        val iconRes = if (isFavorite) {
            R.drawable.ic_favorite // γεμάτη καρδιά
        } else {
            R.drawable.ic_favorite_border // άδεια καρδιά
        }
        binding.btnFavorite.setImageResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
