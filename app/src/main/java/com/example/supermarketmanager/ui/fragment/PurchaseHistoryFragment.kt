package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.supermarketmanager.databinding.FragmentPurchaseHistoryBinding
import com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter
import com.example.supermarketmanager.ui.viewmodel.PurchaseHistoryViewModel

class PurchaseHistoryFragment : Fragment() {
    private var _binding: FragmentPurchaseHistoryBinding? = null
    private val binding get() = _binding!!
    private val vm: PurchaseHistoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentPurchaseHistoryBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.history.observe(viewLifecycleOwner) { list ->
            binding.rvHistory.adapter = PurchaseHistoryAdapter(list)
        }
        vm.loadHistory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
