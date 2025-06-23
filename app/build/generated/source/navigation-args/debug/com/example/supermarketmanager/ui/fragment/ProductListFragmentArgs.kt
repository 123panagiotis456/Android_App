package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class ProductListFragmentArgs(
  public val categoryId: Int
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("categoryId", this.categoryId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("categoryId", this.categoryId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ProductListFragmentArgs {
      bundle.setClassLoader(ProductListFragmentArgs::class.java.classLoader)
      val __categoryId : Int
      if (bundle.containsKey("categoryId")) {
        __categoryId = bundle.getInt("categoryId")
      } else {
        throw IllegalArgumentException("Required argument \"categoryId\" is missing and does not have an android:defaultValue")
      }
      return ProductListFragmentArgs(__categoryId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ProductListFragmentArgs {
      val __categoryId : Int?
      if (savedStateHandle.contains("categoryId")) {
        __categoryId = savedStateHandle["categoryId"]
        if (__categoryId == null) {
          throw IllegalArgumentException("Argument \"categoryId\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"categoryId\" is missing and does not have an android:defaultValue")
      }
      return ProductListFragmentArgs(__categoryId)
    }
  }
}
