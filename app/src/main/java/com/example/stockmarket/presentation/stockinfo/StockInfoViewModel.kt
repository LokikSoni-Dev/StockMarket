package com.example.stockmarket.presentation.stockinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.domain.usecase.IntraDayInfoUseCase
import com.example.stockmarket.util.Result
import com.example.stockmarket.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fetch the data from [StockRepository] and [IntraDayInfoUseCase]
 * and mapping it to state [StockInfoState] to show in UI and
 * ensure UI is up to date on configuration changes like rotation.
 *
 * @author Lokik Soni
 * Created On 27-05-2022
 */
@HiltViewModel
class StockInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val _intraDayInfoUseCase: IntraDayInfoUseCase,
    private val _stockRepository: StockRepository,
): ViewModel() {

    private val _stockInfoState = MutableStateFlow(StockInfoState())
    val stockInfoState = _stockInfoState.asStateFlow()
        .stateIn(
            viewModelScope,
            WhileViewSubscribed,
            StockInfoState()
        )

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("symbol")?.let { symbol ->

                // TODO optimize this
                coroutineScope {
                    _stockRepository.getStockInfo(symbol).collect { stockInfoResult ->
                        _stockInfoState.update { stockInfoState ->
                            when(stockInfoResult) {
                                is Result.Success -> {
                                    stockInfoState.copy(
                                        stockInfo = stockInfoResult.data,
                                        isLoading = false,
                                        errMsgStockInfo = null
                                    )
                                }
                                is Result.Error -> {
                                    stockInfoState.copy(
                                        errMsgStockInfo = stockInfoResult.exception.userMessage,
                                        isLoading = false,
                                    )
                                }
                                is Result.Loading -> {
                                    stockInfoState.copy(isLoading = true)
                                }
                            }
                        }
                    }
                }
                coroutineScope {
                    _intraDayInfoUseCase(symbol).collect { intraDayInfoResult ->
                        _stockInfoState.update { stockInfoState ->

                            when(intraDayInfoResult) {
                                is Result.Success -> {
                                    stockInfoState.copy(
                                        intraDayInfo = intraDayInfoResult.data,
                                        isLoading = false,
                                        errMsgIntraDayInfo = null
                                    )
                                }
                                is Result.Error -> {
                                   stockInfoState.copy(
                                       errMsgIntraDayInfo = intraDayInfoResult.exception.userMessage,
                                       isLoading = false,
                                    )
                                }
                                is Result.Loading -> {
                                    stockInfoState.copy(isLoading = true)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}