package com.example.stockmarket.presentation.navigation


// Route name for nested graph.
const val SPLASH_ROUTE = "splash"
const val HOME_ROUTE = "home"

/**
 * Contain the routes for navigation between screens.
 *
 * @author Lokik Soni
 * Created On 20-05-2022
 */
sealed class Screen(val route: String) {
    object StockListings: Screen("stock_listing")
    object StockInfo: Screen("stock_info")
}