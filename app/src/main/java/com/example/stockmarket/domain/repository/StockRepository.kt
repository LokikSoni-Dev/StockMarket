package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.domain.model.StockInfo
import com.example.stockmarket.domain.model.StockListing
import com.example.stockmarket.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * To provide stocks data from data layer to the UI layer.
 *
 * This follow DI(Dependency Inversion) principle of SOLID.
 * i.e UI layer doesn't directly dependent on data layer
 * both depend on abstraction.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
interface StockRepository {

    /**
     * Provide the list of Stock.
     * [fetchFromRemote] to decide whether to get data from API or local DB.
     * [query] to filter data from DB.
     */
    suspend fun getStockListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Result<List<StockListing>>>

    /**
     * Provide the list of [IntraDayInfo] for [symbol].
     */
    suspend fun getIntraDayInfo(
        symbol: String
    ): Flow<Result<List<IntraDayInfo>>>

    /**
     * Provide the [StockInfo] for [symbol].
     */
    suspend fun getStockInfo(
        symbol: String
    ): Flow<Result<StockInfo>>
}