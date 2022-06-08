package com.example.stockmarket.data.csv

import com.example.stockmarket.di.IoDispatcher
import com.example.stockmarket.domain.model.StockListing
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineDispatcher
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A parser class to parse [InputStream] into
 * [List] of [StockListing].
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@Singleton
class StockListingsParser @Inject constructor(
    @IoDispatcher private val _dispatcher: CoroutineDispatcher
): CSVParser<StockListing>(_dispatcher) {

    override suspend fun execute(inputStream: InputStream): List<StockListing> {
        val csvParser = CSVReader(InputStreamReader(inputStream))
        return try {
            csvParser
                .readAll()
                .drop(1)    // Drop the first row of table i.e column name like symbol, name, exchange
                .mapNotNull { row ->
                    StockListing(
                        symbol = row.getOrNull(0) ?: return@mapNotNull null,    // If null it will ignore this entry
                        name = row.getOrNull(1) ?: return@mapNotNull null,
                        exchange = row.getOrNull(2) ?: return@mapNotNull null
                    )
                }.also {
                    csvParser.close()
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

/**
 * |---------------------------------------------------------------------
 * |    symbol	|                name	               |     exchange   |
 * |--------------------------------------------------------------------|
 * |    A	    |        Agilent Technologies Inc	   |     NYSE       |
 * |    AA	    |        Alcoa Corp	                   |     NYSE       |
 * |    AAA	    |        AAF FIRST PRIORITY CLO   	   |     NYSE ARCA  |
 * |    AAAU	|        Goldman Sachs Physical  	   |     BATS       |
 * |    AAC	    |        Ares Acquisition Corporation  |  	NYSE        |
 * |    AAC-U   |        Ares Acquisition Corporation  |  	NYSE        |
 * |    AAC-WS	|        Ares Acquisition Corporation  |  	NYSE        |
 * |---------------------------------------------------------------------
 */