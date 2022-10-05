package com.example.stockmarket.data.repository

import com.example.stockmarket.data.csv.CSVParser
import com.example.stockmarket.data.local.StockDao
import com.example.stockmarket.data.mapper.toStockInfo
import com.example.stockmarket.data.mapper.toStockListing
import com.example.stockmarket.data.mapper.toStockListingEntity
import com.example.stockmarket.data.remote.StockApi
import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.domain.model.StockListing
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Result
import com.example.stockmarket.util.StockMarketException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A simple [StockRepositoryImpl] decide weather to fetch
 * data from API or local DB and cache the fetched data.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@Singleton
class StockRepositoryImpl @Inject constructor(
    private val _stockDao: StockDao,
    private val _stockApi: StockApi,
    private val _stockListingsParser: CSVParser<StockListing>,
    private val _intraDayInfoParser: CSVParser<IntraDayInfo>,
): StockRepository {

    override suspend fun getStockListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Result<List<StockListing>>> {
        return flow {
            emit(Result.Loading)

            // If 'Initial stage' => Return an empty list because there is nothing in DB.
            // If Not 'Initial stage' then:
            // If query.isBlank() => Return all stocks.
            // If query is like 'TATA' which exist in DB => Return only TATA stocks.
            // If query is like 'xyz' which doesn't exist in DB => Return an empty list.
            val localStockListing = _stockDao.searchStockListing(query)

            // We will consider DB empty only if it is in initial stage and there is nothing in DB.
            // Not when we search for stock using query like 'xyz' which doesn't exist in DB
            // So we have checked query.isBlank() also.
            val isDbEmpty = localStockListing.isEmpty() && query.isBlank()

            // load data from local db and return only if (DB not empty && fetchFromRemote false)
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(
                    Result.Success(
                        data = localStockListing.map { it.toStockListing() }
                    )
                )
                return@flow
            }

            // We will call API only if it is initial stage and we have nothing in DB
            // OR when fetchFromRemote is true.
            if (isDbEmpty || fetchFromRemote) {
                val stockListing = try {
                    val response = _stockApi.getStockListings()
                    // If the list is empty return null
                    _stockListingsParser(response.byteStream()).ifEmpty { null }

                } catch (e: IOException) {
                    e.printStackTrace()
                    null
                } catch (e: HttpException) {
                    e.printStackTrace()
                    null
                }

                stockListing?.let { list ->
                    _stockDao.clearStockListings()
                    _stockDao.insertStockListings(list.map { it.toStockListingEntity() })

                    // Single source of truth i.e local DB.
                    // Even if the API is called we will first save in DB then emit from DB.
                    emit(
                        Result.Success(
                            data = _stockDao.searchStockListing("").map { it.toStockListing() }
                        )
                    )
                } ?: run {
                    emit(Result.Error(StockMarketException.FailedToGetStockListing))
                }
            }
        }
    }

    override suspend fun getIntraDayInfo(symbol: String) = flow {
        emit(Result.Loading)

        val intraDayInfo =  try {
            val response = _stockApi.getIntraDayInfo(symbol)
            _intraDayInfoParser(response.byteStream()).ifEmpty { null }

        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            null
        }

        intraDayInfo?.let { list ->
            emit(Result.Success(list))
        } ?: kotlin.run {
            emit(Result.Error(StockMarketException.FailedToGetIntraDayInfo))
        }
    }

    override suspend fun getStockInfo(symbol: String) = flow {
        emit(Result.Loading)

        val stockInfo = try {
           _stockApi.getStockInfo(symbol).takeIf { it.symbol != null }

        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            null
        }

        stockInfo?.let { stock ->
            emit(Result.Success(stock.toStockInfo()))
        } ?: kotlin.run {
            emit(Result.Error(StockMarketException.FailedToGetStockInfo))
        }
    }
}