package com.example.stockmarket.data.local

import androidx.room.*
import androidx.room.RoomDatabase

/**
 * [StockDao] use [StockDatabase] to perform
 * CRUD operation on data(entity).
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
@Database(
    entities = [StockListingEntity::class],
    version = 1,
)
abstract class StockDatabase: RoomDatabase() {

    abstract val dao: StockDao

    companion object {
        const val DATABASE_NAME = "stock_db"
    }
}