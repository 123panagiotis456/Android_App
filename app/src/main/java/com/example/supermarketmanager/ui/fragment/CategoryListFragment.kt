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

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    private val vm: CategoryViewModel by viewModels()

    private val adapter = CategoryAdapter { category ->
        val action = CategoryListFragmentDirections
            .actionCategoryListFragmentToProductListFragment(
                categoryId = category.id,
                categoryName = category.name // ✅ περνάμε και το όνομα
            )
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_categoryListFragment_to_purchaseHistoryFragment)
        }
        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_categoryListFragment_to_ShoppingCartFragment)
        }
        binding.btnWishlist.setOnClickListener {
            findNavController().navigate(R.id.action_categoryListFragment_to_wishlistFragment)
        }


        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "PEPE MARKET"
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = adapter

        vm.categories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        vm.loadAllCategories()


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
