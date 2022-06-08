package com.example.stockmarket.data.csv

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.InputStream

/**
 * Executes the CSV parsing synchronously
 * or asynchronously using Coroutines.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
abstract class CSVParser<T>(
    private val _coroutineDispatcher: CoroutineDispatcher
) {

    /**
     * Executes the parsing asynchronously and returns a List.
     */
    suspend fun parse(inputStream: InputStream): List<T> {
        return withContext(_coroutineDispatcher) {
            execute(inputStream)
        }
    }

    /**
     * Override this to set the parsing code to be executed.
     */
    protected abstract suspend fun execute(inputStream: InputStream): List<T>
}