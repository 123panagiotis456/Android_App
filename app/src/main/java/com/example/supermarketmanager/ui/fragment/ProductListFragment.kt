package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTitle.text = args.categoryName

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupRecyclerView()
        observeViewModel()
        observeCart()

        vm.filterProducts(
            categoryId = args.categoryId,
            maxPrice = null,
            availableOnly = null
        )
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            viewModel = vm,
            onClick = { product ->
                val action = ProductListFragmentDirections
                    .actionProductListFragmentToProductDetailFragment(product.id)
                findNavController().navigate(action)
            }
        )
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = adapter
    }

    private fun observeViewModel() {
        vm.products.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun observeCart() {
        vm.cartItems.observe(viewLifecycleOwner) { itemList ->
            val total = itemList.sumOf { it.pricePerUnit * it.quantity }

            // Ενημέρωση ποσοτήτων στον adapter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.apply {
            queryHint = "Αναζήτηση προϊόντος"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()) {
                        vm.filterProducts(args.categoryId, null, null)
                    } else {
                        vm.searchProducts(newText.trim())
                    }
                    return true
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
