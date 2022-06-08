package com.example.stockmarket.util

import android.util.Log

/**
 * All possible [Exception] that can be thrown by StockMarket App.
 *
 * @author Lokik Soni
 * Created On 28-05-2022
 */
sealed class StockMarketException(
    private val msg: String,
    val userMessage: String? = null
): Exception(msg) {

    fun print() {
        Log.e("StockMarket", "StockMarket Exception: $msg")
        printStackTrace()
    }

    object FailedToGetStockListing: StockMarketException(
        "Failed to get StockListing: Due to I/O or Http exception.",
        "Could not load Stock listings."
    )
    object FailedToGetIntraDayInfo: StockMarketException(
        "Failed to get IntraDay Info: Due to I/O or Http exception.",
        "Could not load Intra day info."
    )
    object FailedToGetStockInfo: StockMarketException(
        "Failed to get Stock Info: Due to I/O or Http exception.",
        "Could not load StockInfo."
    )
}