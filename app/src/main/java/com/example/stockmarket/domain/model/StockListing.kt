package com.example.stockmarket.domain.model

/**
 * A plain kotlin data class to represent data to the UI.
 *
 * @author Lokik Soni
 * Created On 18/05/2022
 */
data class StockListing(
    val name: String,
    val symbol: String,
    val exchange: String,
)
