package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.supermarketmanager.R
import kotlin.Int
import kotlin.String

public class CategoryListFragmentDirections private constructor() {
  private data class ActionCategoryListFragmentToProductListFragment(
    public val categoryId: Int = -1,
    public val categoryName: String = "Κατηγορία",
  ) : NavDirections {
    public override val actionId: Int = R.id.action_categoryListFragment_to_productListFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("categoryId", this.categoryId)
        result.putString("categoryName", this.categoryName)
        return result
      }
  }

  public companion object {
    public fun actionCategoryListFragmentToProductListFragment(categoryId: Int = -1,
        categoryName: String = "Κατηγορία"): NavDirections =
        ActionCategoryListFragmentToProductListFragment(categoryId, categoryName)

    public fun actionCategoryListFragmentToShoppingCartFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_ShoppingCartFragment)

    public fun actionCategoryListFragmentToPurchaseHistoryFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_purchaseHistoryFragment)

    public fun actionCategoryListFragmentToWishlistFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_categoryListFragment_to_WishlistFragment)
  }
}
