package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.supermarketmanager.R
import kotlin.Int

public class WishlistFragmentDirections private constructor() {
  private data class ActionWishlistFragmentToProductDetailFragment(
    public val productId: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_wishlistFragment_to_productDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun actionWishlistFragmentToProductDetailFragment(productId: Int): NavDirections =
        ActionWishlistFragmentToProductDetailFragment(productId)
  }
}
