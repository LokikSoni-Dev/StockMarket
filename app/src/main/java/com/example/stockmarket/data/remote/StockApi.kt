package com.example.stockmarket.data.remote

import com.example.stockmarket.data.remote.dto.StockInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [StockApi] interface to define all methods
 * related to API request.
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
interface StockApi {

    /**
     * @return [ResponseBody] to simply access to the byteStream.
     */
    @GET("query?function=LISTING_STATUS")
    suspend fun getStockListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    /**
     * @return [ResponseBody] to simply access to the byteStream.
     */
    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntraDayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    /**
     * Parse th json into [StockInfoDto]
     * and return.
     */
    @GET("query?function=OVERVIEW")
    suspend fun getStockInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): StockInfoDto

    companion object {
        const val API_KEY = "EFXGGQ7DV2FNL6M9"
        const val BASE_URL = "https://alphavantage.co"
    }
}