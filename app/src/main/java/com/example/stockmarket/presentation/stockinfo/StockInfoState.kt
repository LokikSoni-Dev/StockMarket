package com.example.stockmarket.presentation.stockinfo

import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.domain.model.StockInfo

/**
 * Hold all the possible states of the [StockInfoScreen]
 * including initial state.
 *
 * @author Lokik Soni
 * Created On 27-05-2022
 */
data class StockInfoState(
    val intraDayInfo: List<IntraDayInfo> = emptyList(),
    val stockInfo: StockInfo? = null,
    val isLoading: Boolean = false,
    val errMsgStockInfo: String? = null,
    val errMsgIntraDayInfo: String? = null,
)
