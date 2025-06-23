package com.example.supermarketmanager;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.supermarketmanager.databinding.FragmentCategoryListBindingImpl;
import com.example.supermarketmanager.databinding.FragmentProductDetailBindingImpl;
import com.example.supermarketmanager.databinding.FragmentProductListBindingImpl;
import com.example.supermarketmanager.databinding.FragmentPurchaseHistoryBindingImpl;
import com.example.supermarketmanager.databinding.FragmentShoppingListBindingImpl;
import com.example.supermarketmanager.databinding.FragmentWishlistBindingImpl;
import com.example.supermarketmanager.databinding.ItemCategoryBindingImpl;
import com.example.supermarketmanager.databinding.ItemProductBindingImpl;
import com.example.supermarketmanager.databinding.ItemPurchaseHistoryBindingImpl;
import com.example.supermarketmanager.databinding.ItemShoppingListItemBindingImpl;
import com.example.supermarketmanager.databinding.ItemWishlistItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTCATEGORYLIST = 1;

  private static final int LAYOUT_FRAGMENTPRODUCTDETAIL = 2;

  private static final int LAYOUT_FRAGMENTPRODUCTLIST = 3;

  private static final int LAYOUT_FRAGMENTPURCHASEHISTORY = 4;

  private static final int LAYOUT_FRAGMENTSHOPPINGLIST = 5;

  private static final int LAYOUT_FRAGMENTWISHLIST = 6;

  private static final int LAYOUT_ITEMCATEGORY = 7;

  private static final int LAYOUT_ITEMPRODUCT = 8;

  private static final int LAYOUT_ITEMPURCHASEHISTORY = 9;

  private static final int LAYOUT_ITEMSHOPPINGLISTITEM = 10;

  private static final int LAYOUT_ITEMWISHLISTITEM = 11;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(11);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_category_list, LAYOUT_FRAGMENTCATEGORYLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_product_detail, LAYOUT_FRAGMENTPRODUCTDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_product_list, LAYOUT_FRAGMENTPRODUCTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_purchase_history, LAYOUT_FRAGMENTPURCHASEHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_shopping_list, LAYOUT_FRAGMENTSHOPPINGLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.fragment_wishlist, LAYOUT_FRAGMENTWISHLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.item_category, LAYOUT_ITEMCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.item_product, LAYOUT_ITEMPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.item_purchase_history, LAYOUT_ITEMPURCHASEHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.item_shopping_list_item, LAYOUT_ITEMSHOPPINGLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.supermarketmanager.R.layout.item_wishlist_item, LAYOUT_ITEMWISHLISTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTCATEGORYLIST: {
          if ("layout/fragment_category_list_0".equals(tag)) {
            return new FragmentCategoryListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_category_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTDETAIL: {
          if ("layout/fragment_product_detail_0".equals(tag)) {
            return new FragmentProductDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTLIST: {
          if ("layout/fragment_product_list_0".equals(tag)) {
            return new FragmentProductListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPURCHASEHISTORY: {
          if ("layout/fragment_purchase_history_0".equals(tag)) {
            return new FragmentPurchaseHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_purchase_history is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHOPPINGLIST: {
          if ("layout/fragment_shopping_list_0".equals(tag)) {
            return new FragmentShoppingListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shopping_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWISHLIST: {
          if ("layout/fragment_wishlist_0".equals(tag)) {
            return new FragmentWishlistBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_wishlist is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCATEGORY: {
          if ("layout/item_category_0".equals(tag)) {
            return new ItemCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_category is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCT: {
          if ("layout/item_product_0".equals(tag)) {
            return new ItemProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPURCHASEHISTORY: {
          if ("layout/item_purchase_history_0".equals(tag)) {
            return new ItemPurchaseHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_purchase_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSHOPPINGLISTITEM: {
          if ("layout/item_shopping_list_item_0".equals(tag)) {
            return new ItemShoppingListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_shopping_list_item is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMWISHLISTITEM: {
          if ("layout/item_wishlist_item_0".equals(tag)) {
            return new ItemWishlistItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_wishlist_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(11);

    static {
      sKeys.put("layout/fragment_category_list_0", com.example.supermarketmanager.R.layout.fragment_category_list);
      sKeys.put("layout/fragment_product_detail_0", com.example.supermarketmanager.R.layout.fragment_product_detail);
      sKeys.put("layout/fragment_product_list_0", com.example.supermarketmanager.R.layout.fragment_product_list);
      sKeys.put("layout/fragment_purchase_history_0", com.example.supermarketmanager.R.layout.fragment_purchase_history);
      sKeys.put("layout/fragment_shopping_list_0", com.example.supermarketmanager.R.layout.fragment_shopping_list);
      sKeys.put("layout/fragment_wishlist_0", com.example.supermarketmanager.R.layout.fragment_wishlist);
      sKeys.put("layout/item_category_0", com.example.supermarketmanager.R.layout.item_category);
      sKeys.put("layout/item_product_0", com.example.supermarketmanager.R.layout.item_product);
      sKeys.put("layout/item_purchase_history_0", com.example.supermarketmanager.R.layout.item_purchase_history);
      sKeys.put("layout/item_shopping_list_item_0", com.example.supermarketmanager.R.layout.item_shopping_list_item);
      sKeys.put("layout/item_wishlist_item_0", com.example.supermarketmanager.R.layout.item_wishlist_item);
    }
  }
}
