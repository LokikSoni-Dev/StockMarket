package com.example.stockmarket.presentation.navigation.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.stockmarket.presentation.navigation.HOME_ROUTE

/**
 * [NavHost] is a container to navigate between screens via [NavHostController].
 *
 * [NavHostController] keeps the track of the backStack and state of each screen.
 * Creating the [NavHostController] here so that all composable that need
 * it have access to it.
 *
 * [startDestination] in [NavHost] decide which graph to host first.
 *
 * @author Lokik Soni
 * Created On 20-05-2022
 */
@ExperimentalMaterialApi
@Composable
fun StockMarketNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    // Each NavController must be associated with a single NavHost composable.
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        // Nested Navigation by grouping splash and home screens.
//        splashNavGraph(navHostController = navController)
        homeNavGraph(navHostController = navController)
    }
}