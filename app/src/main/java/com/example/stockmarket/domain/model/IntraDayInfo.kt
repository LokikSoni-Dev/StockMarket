package com.example.stockmarket.domain.model

import java.time.LocalDateTime

/**
 * A plain kotlin data class to represent data to the UI.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
data class IntraDayInfo(
    val date: LocalDateTime?,
    val close: Double
)
