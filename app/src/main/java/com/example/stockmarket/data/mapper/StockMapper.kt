package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.local.StockListingEntity
import com.example.stockmarket.data.remote.dto.StockInfoDto
import com.example.stockmarket.domain.model.StockInfo
import com.example.stockmarket.domain.model.StockListing

/**
 * Helper extension function to remove the dependency between
 * data and domain layer.
 * Suppose: If we want to change our API or DB then we don't
 * need to change the domain level StockListing models
 * because they are independent from library used in data layer
 * they are pure kotlin data class.
 * So we only need to change entities like StockListingEntity because it is
 * using annotations come from library use for data layer.
 */

/**
 * Map the Local storage data to the data used for UI.
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
fun StockListingEntity.toStockListing() = StockListing(
    name = name,
    symbol = symbol,
    exchange = exchange
)

/**
 * Map the remote data to the Local storage entity to save in DB.
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
fun StockListing.toStockListingEntity() = StockListingEntity(
    name = name,
    symbol = symbol,
    exchange = exchange
    // ID will be auto generated for this.
)

/**
 * Map the remote Dto to the data used for UI.
 *
 * @author Lokik Soni
 * Created on 23/05/2022
 */
fun StockInfoDto.toStockInfo() = StockInfo(
    symbol = symbol ?: "",
    description = description ?: "",
    name = name ?: "",
    country = country ?: "",
    industry = industry ?: ""
)