package com.example.stockmarket.presentation.navigation.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.stockmarket.presentation.navigation.HOME_ROUTE
import com.example.stockmarket.presentation.navigation.Screen
import com.example.stockmarket.presentation.stockinfo.StockInfoScreen
import com.example.stockmarket.presentation.stockinfo.StockInfoViewModel
import com.example.stockmarket.presentation.stocklisting.StockListingsScreen
import com.example.stockmarket.presentation.stocklisting.StockListingsViewModel

/**
 * Grouping the home related screens using [NavGraphBuilder].
 *
 * @author Lokik Soni
 * Created On 20-05-2022
 */
@ExperimentalMaterialApi
fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {
    // startDestination in NavGraph navigation decide which screen to host first.
    navigation(startDestination = Screen.StockListings.route, route = HOME_ROUTE) {

        composable(Screen.StockListings.route) {
            val stockViewModel: StockListingsViewModel = hiltViewModel()
            StockListingsScreen(
                navController = navHostController,
                viewModel = stockViewModel
            )
        }

        // To pass argument, add argument 'placeholder' to your route like => route + "/{symbol}"
        // By default, all arguments are parsed as strings.
        // You can specify another type by using the arguments parameter.
        composable(
            Screen.StockInfo.route + "/{symbol}",
//            arguments = listOf(navArgument("symbol") { type = NavType.StringType })
        ) { backStackEntry ->
            val stockInfoViewModel: StockInfoViewModel = hiltViewModel()

            // Extract the arguments from the backStackEntry.
            StockInfoScreen(
           //     symbol = backStackEntry.arguments?.getString("symbol"),
                stockInfoViewModel = stockInfoViewModel
            )
        }
    }
}

/**
 * Grouping the splash related screens using [NavGraphBuilder].
 */
fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController
) {
    // TODO add splash graph here
}