package com.example.stockmarket.data.remote.dto

import com.squareup.moshi.Json

/**
 * Data transfer object (DTO) to map json
 * into data class.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
data class StockInfoDto(
    // Api respond with different names in Json.
    // So in moshi we use @field:Json(name = "Symbol") to
    // define our custom name because we don't want to use
    // 'Symbol' variable at our end so we have changed it to 'symbol'.
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,     // If we exceed limit of 'in 1 min only 5 call' then we get null.
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?,
)
