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

    enum class SortOption { DEFAULT, PRICE, DISCOUNT, UNIT_PRICE }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Floating X: κλείσιμο του sheet
        binding.btnClose.setOnClickListener { dismiss() }

        binding.btnApply.setOnClickListener {
            val selected = when (binding.rgSort.checkedRadioButtonId) {
                binding.rbPrice.id -> SortOption.PRICE
                else -> SortOption.DEFAULT
            }
            onApplySort(selected)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            bottomSheet?.requestLayout()
            // Κάνε το BottomSheet να ανοίγει ΠΑΝΤΑ fully expanded!
            bottomSheet?.let { bs ->
                BottomSheetBehavior.from(bs).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
