package com.example.supermarketmanager.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.supermarketmanager.R
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

internal fun Fragment.observeCartErrors(viewModel: ProductViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.cartErrors.collect { error ->
                val message = when (error) {
                    ProductViewModel.CartError.STOCK -> R.string.purchase_order_stock
                    ProductViewModel.CartError.UPDATE -> R.string.cart_update_failed
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
