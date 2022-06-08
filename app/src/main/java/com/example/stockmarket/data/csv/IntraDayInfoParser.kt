package com.example.stockmarket.data.csv

import com.example.stockmarket.data.mapper.toIntraDayInfo
import com.example.stockmarket.data.remote.dto.IntraDayInfoDto
import com.example.stockmarket.di.IoDispatcher
import com.example.stockmarket.domain.model.IntraDayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineDispatcher
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A parser class to parse [InputStream] into
 * [List] of [IntraDayInfo].
 *
 * @author Lokik Soni
 * Created On 26-05-2022
 */
@Singleton
class IntraDayInfoParser @Inject constructor(
    @IoDispatcher private val _dispatcher: CoroutineDispatcher
): CSVParser<IntraDayInfo>(_dispatcher) {

    override suspend fun execute(inputStream: InputStream): List<IntraDayInfo> {
        val csvParser = CSVReader(InputStreamReader(inputStream))
        return try {
            csvParser
                .readAll()
                .drop(1)    // Drop the first row of table i.e column name like timestamp & close
                .mapNotNull { row ->
                    val intraDayInfoDto = IntraDayInfoDto(
                        timeStamp = row.getOrNull(0) ?: return@mapNotNull null,
                        close = row.getOrNull(4)?.toDouble() ?: return@mapNotNull null
                    )
                    intraDayInfoDto.toIntraDayInfo()

                }.also {
                    csvParser.close()
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}