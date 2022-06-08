package com.example.stockmarket.presentation.stocklisting

import com.example.stockmarket.domain.model.StockListing

/**
 * Hold all the possible states of the [StockListingsScreen]
 * including initial state.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
data class StockListingsState(
    val stocks: List<StockListing> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)
