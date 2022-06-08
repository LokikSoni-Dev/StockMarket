package com.example.stockmarket.presentation.stocklisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Result
import com.example.stockmarket.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Fetch the data from [StockRepository] and mapping it
 * to state [StockListingsState] to show in UI and
 * ensure UI is up to date on configuration changes like rotation.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@HiltViewModel
class StockListingsViewModel @Inject constructor(
    private val _stockRepository: StockRepository
): ViewModel() {

    private val _stockListingsState = MutableStateFlow(StockListingsState())
    val stockState = _stockListingsState.stateIn(
        viewModelScope, WhileViewSubscribed, StockListingsState()
    )

    // To keep track of coroutine job
    private var searchJob: Job? = null

    init {
        // Initially Loads all the stocks.
        getStockListings()
    }

    /**
     * Handle events related to the [StockListingsScreen].
     */
    fun onEvent(event: StockListingsEvent) {

        when(event) {
            is StockListingsEvent.Refresh -> {
                getStockListings(fetchFromRemote = true)
            }
            // This is called for every single character.
            // Lets says we type 'tes'.
            // So First we type 't' and before it search 't'
            // we type another character 'e' this block will
            // triggered again and cancel the current job.
            // This way it will never reach to the [getStockListings]
            // until we stop typing at least 500 ms.
            // This way we will save lots of query search
            is StockListingsEvent.OnStockSearch -> {
                _stockListingsState.update { it.copy(searchQuery = event.query) }
                searchJob?.cancel()     // If we already have searchJob running cancel it.
                // And relaunch the Job
                searchJob = viewModelScope.launch {
                    // We add little delay of 500 ms until we start a search
                    // oo avoid making too many queries for every single character.
                    delay(500L)
                    // After 500 ms get the stock listings.
                    getStockListings()
                }
            }
        }
    }

    /**
     * Get the list of stock and map to the
     * state [StockListingsState] to show to the UI.
     */
    private fun getStockListings(
        fetchFromRemote: Boolean = false,
        query: String = _stockListingsState.value.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            _stockRepository.getStockListings(fetchFromRemote, query).collect { result ->
                _stockListingsState.update { stockListingsState ->
                    when(result) {
                        is Result.Success -> {
                            stockListingsState.copy(
                                stocks = result.data,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                        is Result.Error -> {
                            stockListingsState.copy(
                                errorMessage = result.exception.userMessage,
                                isLoading = false,
                            )
                        }
                        is Result.Loading -> {
                            stockListingsState.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}