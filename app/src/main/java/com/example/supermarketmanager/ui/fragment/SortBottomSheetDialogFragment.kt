package com.example.supermarketmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.supermarketmanager.databinding.BottomSheetSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SortBottomSheetDialogFragment(
    private val onApplySort: (SortOption) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetSortBinding? = null
    private val binding get() = _binding!!

    // Sorting options enum
    enum class SortOption { DEFAULT, PRICE, DISCOUNT, UNIT_PRICE }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate view with exception handling
        return try {
            _binding = BottomSheetSortBinding.inflate(inflater, container, false)
            binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Floating X: close the sheet
        binding.btnClose.setOnClickListener {
            try {
                dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Apply button: apply selected sorting and close
        binding.btnApply.setOnClickListener {
            try {
                val selected = when (binding.rgSort.checkedRadioButtonId) {
                    binding.rbPrice.id -> SortOption.PRICE
                    // You can add more cases for DISCOUNT, UNIT_PRICE if you have radio buttons for them
                    else -> SortOption.DEFAULT
                }
                onApplySort(selected)
                dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Make BottomSheet always fully expanded on open
        try {
            dialog?.let {
                val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
                bottomSheet?.requestLayout()
                bottomSheet?.let { bs ->
                    BottomSheetBehavior.from(bs).state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
