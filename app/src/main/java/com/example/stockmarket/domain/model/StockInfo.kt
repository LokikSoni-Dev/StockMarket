package com.example.stockmarket.domain.model

/**
 * A plain kotlin data class to represent data to the UI.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
data class StockInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String,
)
