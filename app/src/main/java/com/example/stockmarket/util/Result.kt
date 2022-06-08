package com.example.stockmarket.util

/**
 * A generic class that holds data form data layer
 * with its status like [Success], [Error] or [Loading].
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: StockMarketException): Result<Nothing>()
    object Loading: Result<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

/**
 * Why we use Result sealed class.
 * When we deal with Remote api or Local DB then it can be the case
 * that something goes wrong and we want to catch that and get error
 * in viewModel to show to the UI. For this we use Result sealed class.
 */
