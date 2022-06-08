package com.example.stockmarket.data.remote.dto

import com.squareup.moshi.Json

/**
 * Data transfer object (DTO) to map json
 * into data class.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
data class IntraDayInfoDto(
    @field:Json(name = "timestamp") val timeStamp: String,
    val close: Double
)
