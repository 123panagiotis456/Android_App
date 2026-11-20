package com.example.supermarketmanager.ui.fragment

import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.supermarketmanager.MainActivity
import com.example.supermarketmanager.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogFiltersTest {

    @Test
    fun filtersKeepSelectionWhenRecreatedAndReopenWithAppliedValues() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val host = navHost(activity)
                host.navController.navigate(
                    R.id.action_categoryListFragment_to_productListFragment,
                    bundleOf("categoryId" to 1, "categoryName" to "Fruits")
                )
                host.childFragmentManager.executePendingTransactions()
                activity.findViewById<View>(R.id.btnFilters).performClick()
                val sheet = filterSheet(activity)
                sheet.requireView().findViewById<RadioButton>(R.id.rbPrice).performClick()
                sheet.requireView().findViewById<CheckBox>(R.id.availableOnly).performClick()
            }

            scenario.recreate()
            scenario.onActivity { activity ->
                val sheet = filterSheet(activity)
                assertSelections(sheet)
                sheet.requireView().findViewById<View>(R.id.btnApply).performClick()
                navHost(activity).childFragmentManager.executePendingTransactions()
                assertEquals(R.id.productListFragment, navHost(activity).navController.currentDestination?.id)
                activity.findViewById<View>(R.id.btnFilters).performClick()
                assertSelections(filterSheet(activity))
            }
        }
    }

    private fun navHost(activity: MainActivity): NavHostFragment =
        activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    private fun filterSheet(activity: MainActivity): SortBottomSheetDialogFragment =
        navHost(activity).childFragmentManager.findFragmentByTag(SortBottomSheetDialogFragment.REQUEST)
            as SortBottomSheetDialogFragment

    private fun assertSelections(sheet: SortBottomSheetDialogFragment) {
        val view = sheet.requireView()
        assertTrue(view.findViewById<RadioButton>(R.id.rbPrice).isChecked)
        assertTrue(view.findViewById<CheckBox>(R.id.availableOnly).isChecked)
    }
}
