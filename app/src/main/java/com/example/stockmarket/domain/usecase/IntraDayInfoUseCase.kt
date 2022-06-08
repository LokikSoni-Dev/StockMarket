package com.example.stockmarket.domain.usecase

import com.example.stockmarket.di.IoDispatcher
import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case to filter the [List] of [IntraDayInfo]
 * by yesterday date and then sort it by hour.
 *
 * @author Lokik Soni
 * Created On 27-05-2022
 */
@Singleton
class IntraDayInfoUseCase @Inject constructor(
    private val _stockRepository: StockRepository,
    @IoDispatcher private val _dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(symbol: String): Flow<Result<List<IntraDayInfo>>> {

        return _stockRepository.getIntraDayInfo(symbol).map {
            when(val result = it) {
                is Result.Success -> {
                    Result.Success(
                        result.data.filter { intraDayInfo ->
                            // Get the data for last date of market opened
                            intraDayInfo.date?.dayOfMonth == result.data.first().date?.dayOfMonth
                        }.sortedBy { intraDayInfo ->
                            intraDayInfo.date?.hour
                        }
                    )
                }
                else -> result
            }
        }.flowOn(_dispatcher)
    }
}