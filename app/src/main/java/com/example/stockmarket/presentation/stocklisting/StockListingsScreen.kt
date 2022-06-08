package com.example.stockmarket.presentation.stocklisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stockmarket.presentation.common.FullScreenLoading
import com.example.stockmarket.presentation.common.LoadingContent
import com.example.stockmarket.presentation.navigation.Screen
import com.example.stockmarket.util.rememberFlowWithLifecycle

/**
 * Shows the list of stocks with search option.
 *
 * @author Lokik Soni
 * Created On 20-05-2022
 */
@ExperimentalMaterialApi
@Composable
fun StockListingsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: StockListingsViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val stockState by rememberFlowWithLifecycle(
        viewModel.stockState
    ).collectAsState(StockListingsState())

    Scaffold(
        modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = stockState.searchQuery,
                onValueChange = {
                    viewModel.onEvent(
                        StockListingsEvent.OnStockSearch(it)
                    )
                },
                placeholder = {
                    Text(text = "Search Stock...")
                },
                singleLine = true,
                maxLines = 1,
            )
            LoadingContent(
                empty = when {
                    stockState.stocks.isEmpty() -> true
                    else -> false
                },
                emptyContent = { FullScreenLoading() },
                loading = stockState.isLoading,
                onRefresh = { viewModel.onEvent(StockListingsEvent.Refresh) },
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(stockState.stocks) { index, stock ->
                        StockItem(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    // To pass the argument to the destination, you need
                                    // to add the value to the route in place of the placeholder.
                                    navController.navigate(
                                        Screen.StockInfo.route + "/${stock.symbol}"
                                    )
                                }
                                .padding(16.dp),
                            stockListing = stock
                        )

                        if (index < stockState.stocks.size) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }

    val message = stockState.errorMessage
    if (message != null) {

        // Effect running in a coroutine that displays the Snackbar on the screen.
        // If there's a change to message or scaffoldState, the previous
        // effect will be cancelled and a new one will start with the new values
        LaunchedEffect(scaffoldState, message) {
            scaffoldState.snackbarHostState.showSnackbar(message = message)
        }
    }
}