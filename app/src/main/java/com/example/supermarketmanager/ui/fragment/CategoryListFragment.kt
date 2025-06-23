package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketmanager.databinding.FragmentCategoryListBinding
import com.example.supermarketmanager.ui.adapter.CategoryAdapter
import com.example.supermarketmanager.ui.viewmodel.CategoryViewModel

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    private val vm: CategoryViewModel by viewModels()
    private val adapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCategoryListBinding
        .inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) Βάζουμε LayoutManager
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())

        // 2) Ορίζουμε τον adapter
        binding.rvCategories.adapter = adapter

        // 3) Παρατηρούμε το LiveData<List<CategoryEntity>>
        vm.categories.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // 4) Φορτώνουμε τις κατηγορίες
        vm.loadAllCategories()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
