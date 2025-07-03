package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.supermarketmanager.R
import kotlin.Int

public class ProductListFragmentDirections private constructor() {
  private data class ActionProductListFragmentToProductDetailFragment(
    public val productId: Int,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_productListFragment_to_productDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun actionProductListFragmentToProductDetailFragment(productId: Int): NavDirections =
        ActionProductListFragmentToProductDetailFragment(productId)
  }
}
