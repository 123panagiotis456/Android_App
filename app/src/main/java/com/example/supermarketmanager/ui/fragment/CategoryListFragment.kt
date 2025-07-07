package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.databinding.FragmentCategoryListBinding
import com.example.supermarketmanager.ui.adapter.CategoryAdapter
import com.example.supermarketmanager.ui.viewmodel.CategoryViewModel

/**
 * Fragment that displays the list of product categories.
 * Supports navigation to history, cart, wishlist, and product list.
 * Implements exception handling for robust navigation and data operations.
 */
class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    private val vm: CategoryViewModel by viewModels()

    // Adapter for category RecyclerView. Navigates to product list on click.
    private val adapter = CategoryAdapter { category ->
        try {
            val action = CategoryListFragmentDirections
                .actionCategoryListFragmentToProductListFragment(
                    categoryId = category.id,
                    categoryName = category.name
                )
            findNavController().navigate(action)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // Enable options menu for search
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout with ViewBinding, handle any exceptions.
        return try {
            _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
            binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            View(requireContext())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // Set up navigation buttons (history, cart, wishlist)
            binding.btnHistory.setOnClickListener {
                safeNavigate(R.id.action_categoryListFragment_to_purchaseHistoryFragment)
            }
            binding.btnCart.setOnClickListener {
                safeNavigate(R.id.action_categoryListFragment_to_ShoppingCartFragment)
            }
            binding.btnWishlist.setOnClickListener {
                safeNavigate(R.id.action_categoryListFragment_to_wishlistFragment)
            }

            // Set up toolbar
            (requireActivity() as? AppCompatActivity)?.apply {
                setSupportActionBar(binding.toolbar)
                supportActionBar?.title = "PEPE MARKET"
                supportActionBar?.setDisplayShowTitleEnabled(true)
            }

            // RecyclerView grid for categories
            binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvCategories.adapter = adapter

            // Observe category list from ViewModel
            vm.categories.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            // Load all categories initially
            vm.loadAllCategories()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        try {
            inflater.inflate(R.menu.menu_search, menu)
            val searchItem = menu.findItem(R.id.action_search)
            val searchView = searchItem.actionView as? SearchView
            searchView?.queryHint = "Αναζήτηση κατηγορίας"
            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    vm.searchCategories(newText.orEmpty())
                    return true
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Helper function to safely perform navigation actions.
     */
    private fun safeNavigate(resId: Int) {
        try {
            findNavController().navigate(resId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
