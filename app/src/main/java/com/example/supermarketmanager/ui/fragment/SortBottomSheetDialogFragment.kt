package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.supermarketmanager.databinding.BottomSheetSortBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = BottomSheetSortBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = BottomSheetSortBinding.bind(view)
        if (savedInstanceState == null) {
            binding.rgSort.check(
                if (arguments?.getBoolean(BY_PRICE) == true) binding.rbPrice.id else binding.rbDefault.id
            )
            binding.availableOnly.isChecked = arguments?.getBoolean(AVAILABLE_ONLY) == true
        }
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnApply.setOnClickListener {
            parentFragmentManager.setFragmentResult(REQUEST, bundleOf(
                BY_PRICE to (binding.rgSort.checkedRadioButtonId == binding.rbPrice.id),
                AVAILABLE_ONLY to binding.availableOnly.isChecked
            ))
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {
        const val REQUEST = "catalog_filters"
        const val BY_PRICE = "by_price"
        const val AVAILABLE_ONLY = "available_only"

        fun newInstance(byPrice: Boolean, availableOnly: Boolean) =
            SortBottomSheetDialogFragment().apply {
                arguments = bundleOf(BY_PRICE to byPrice, AVAILABLE_ONLY to availableOnly)
            }
    }
}
