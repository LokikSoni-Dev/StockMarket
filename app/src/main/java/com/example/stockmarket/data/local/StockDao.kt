package com.example.stockmarket.data.local

import androidx.room.*

/**
 * [StockDao] interface to define all Query methods
 * use to interact with the Database.
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
@Dao
interface StockDao {

    /**
     * Save the [List] of [StockListingEntity]
     * into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockListings(
        stockListingEntity: List<StockListingEntity>
    )

    /**
     * Clear local storage data.
     */
    @Query("DELETE FROM stocklistingentity")
    suspend fun clearStockListings()

    /**
     * Get the stock by either comparing [query] with name
     * or [query] with symbol.
     */
    @Query("""
        SELECT * FROM stocklistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == symbol
    """)
    suspend fun searchStockListing(query: String): List<StockListingEntity>
}

/**
 * Suppose we have Tesla in string query.
 *
 * Example: Compare query string with name.
 * (LOWER(name) LIKE '%' || LOWER(:query) || '%') ==> Lower case the name and query and compare both.
 * (Note in query || is used to concat string) So query will be %tesla%
 *
 * OR
 *
 * Compare query string with symbol.
 * (UPPER(:query) == symbol) ==> Upper case the query string and compare with symbol.
 */