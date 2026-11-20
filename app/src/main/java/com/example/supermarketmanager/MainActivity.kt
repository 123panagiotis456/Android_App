package com.example.supermarketmanager

import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.supermarketmanager.databinding.ActivityMainBinding
import com.example.supermarketmanager.ui.viewmodel.ShoppingListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val cartViewModel: ShoppingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = host.navController
        binding.bottomNav.setOnItemSelectedListener { selectTab(it.itemId) }
        var previousTabId: Int? = null
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val item = destination.hierarchy.firstNotNullOfOrNull { binding.bottomNav.menu.findItem(it.id) }
            binding.bottomNav.isVisible = item != null
            item?.isChecked = true

            // Animate the host so restored tab stacks receive the same transition as new tabs.
            val tabId = item?.itemId
            val switchedTabs = previousTabId != null && tabId != null && previousTabId != tabId
            previousTabId = tabId
            binding.navHostFragment.animate().cancel()
            binding.navHostFragment.alpha = 1f
            if (switchedTabs && (Build.VERSION.SDK_INT < 26 || ValueAnimator.areAnimatorsEnabled())) {
                binding.navHostFragment.alpha = 0.65f
                binding.navHostFragment.animate().alpha(1f).setDuration(180).start()
            }
        }

        cartViewModel.cartItems.observe(this) { items ->
            val quantity = items.sumOf { it.quantity }
            if (quantity == 0) {
                binding.bottomNav.removeBadge(R.id.shoppingCartFragment)
            } else {
                binding.bottomNav.getOrCreateBadge(R.id.shoppingCartFragment).apply {
                    number = quantity
                    backgroundColor = getColor(R.color.market_mint)
                    badgeTextColor = getColor(R.color.market_on_mint)
                }
            }
        }
    }

    fun showCart() {
        binding.bottomNav.selectedItemId = R.id.shoppingCartFragment
    }

    private fun selectTab(tabId: Int): Boolean {
        if (navController.currentDestination?.hierarchy?.any { it.id == tabId } == true) {
            return true
        }
        navController.navigate(tabId, null, navOptions {
            launchSingleTop = true
            restoreState = true
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        })
        return true
    }
}
