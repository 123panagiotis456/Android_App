package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketmanager.R
import com.example.supermarketmanager.databinding.FragmentProductListBinding
import com.example.supermarketmanager.ui.adapter.ProductAdapter
import com.example.supermarketmanager.ui.viewmodel.ProductViewModel

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val args: ProductListFragmentArgs by navArgs()
    private val vm: ProductViewModel by viewModels()

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set toolbar title to the category name passed from arguments
        binding.toolbarTitle.text = args.categoryName

        // Set up the custom toolbar and back button
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }

        // Handle toolbar back navigation
        binding.toolbar.setNavigationOnClickListener {
            try {
                findNavController().popBackStack()
            } catch (e: IllegalStateException) {
                Log.e(TAG, "Back navigation failed", e)
                Toast.makeText(requireContext(), "Unable to navigate back", Toast.LENGTH_SHORT).show()
            }
        }

        setupRecyclerView()
        observeViewModel()
        observeCart()

        // Navigate to shopping cart when clicking on cart banner
        binding.cartBanner.setOnClickListener {
            val action = ProductListFragmentDirections.actionProductListFragmentToShoppingCartFragment()
            try {
                findNavController().navigate(action)
            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "Invalid navigation to cart", e)
                Toast.makeText(requireContext(), "Unable to open cart", Toast.LENGTH_SHORT).show()
            } catch (e: IllegalStateException) {
                Log.e(TAG, "Navigation to cart failed", e)
                Toast.makeText(requireContext(), "Unable to open cart", Toast.LENGTH_SHORT).show()
            }
        }

        // Load initial products for the selected category
        vm.filterProducts(
            categoryId = args.categoryId,
            maxPrice = null,
            availableOnly = null,
        )

        // Show sorting options in a bottom sheet
        binding.btnFilters.setOnClickListener {
            SortBottomSheetDialogFragment { sortOption ->
                when (sortOption) {
                    SortBottomSheetDialogFragment.SortOption.DEFAULT -> {
                        vm.filterProducts(args.categoryId, null, null)
                    }
                    SortBottomSheetDialogFragment.SortOption.PRICE -> {
                        vm.sortProductsByPrice()
                    }
                    SortBottomSheetDialogFragment.SortOption.DISCOUNT -> {
                        vm.sortProductsByDiscount()
                    }
                    SortBottomSheetDialogFragment.SortOption.UNIT_PRICE -> {
                        vm.sortProductsByUnitPrice()
                    }
                }
            }.show(parentFragmentManager, "SortBottomSheetDialogFragment")
        }
    }

    /** Sets up the product RecyclerView with grid layout and adapter */
    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            viewModel = vm,
            onClick = { product ->
                val action =
                    ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product.id)
                try {
                    findNavController().navigate(action)
                } catch (e: IllegalArgumentException) {
                    Log.e(TAG, "Invalid navigation to product detail", e)
                    Toast.makeText(requireContext(), "Unable to open product", Toast.LENGTH_SHORT).show()
                } catch (e: IllegalStateException) {
                    Log.e(TAG, "Navigation to product detail failed", e)
                    Toast.makeText(requireContext(), "Unable to open product", Toast.LENGTH_SHORT).show()
                }
            },
        )
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = adapter
    }

    /** Observes product list from ViewModel and submits it to the adapter */
    private fun observeViewModel() {
        vm.products.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    /** Observes the shopping cart and updates the UI with cart details */
    private fun observeCart() {
        vm.cartItems.observe(viewLifecycleOwner) { itemList ->
            val total = itemList.sumOf { it.pricePerUnit * it.quantity }

            // Update product quantities in the adapter
            for (item in itemList) {
                adapter.updateQuantity(item.productId, item.quantity)
            }

            if (itemList.isNotEmpty()) {
                binding.cartBanner.visibility = View.VISIBLE

                val remaining = (20.0 - total).coerceAtLeast(0.0)
                val percentage = ((total / 20.0) * 100).coerceAtMost(100.0)

                binding.tvCartQuantity.text = itemList.sumOf { it.quantity }.toString()
                binding.tvCartTotal.text = String.format("%.2f€", total)
                binding.tvRemainingAmount.text = if (total < 20.0) {
                    String.format("%.2f€ ακόμα για να συμπληρώσεις το ποσό ελ. παραγγελίας.", remaining)
                } else {
                    "Έφτασες το ελάχιστο ποσό παραγγελίας!"
                }
                binding.progressBar.progress = percentage.toInt()
            } else {
                binding.cartBanner.visibility = View.GONE
            }
        }
    }

    /** Inflates and handles the search menu for products */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.apply {
            queryHint = "Αναζήτηση προϊόντος"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    return try {
                        if (newText.isNullOrBlank()) {
                            vm.filterProducts(args.categoryId, null, null)
                        } else {
                            vm.searchProducts(newText.trim())
                        }
                        true
                    } catch (e: Exception) {
                        Log.e(TAG, "Product search failed", e)
                        Toast.makeText(requireContext(), "Search failed", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ProductListFragment"
    }
}
