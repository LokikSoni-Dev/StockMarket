package com.example.stockmarket.presentation.stocklisting

/**
 * Handle all possible events used in [StockListingsScreen].
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
sealed class StockListingsEvent {
    object Refresh: StockListingsEvent()
    data class OnStockSearch(val query: String): StockListingsEvent()
}
