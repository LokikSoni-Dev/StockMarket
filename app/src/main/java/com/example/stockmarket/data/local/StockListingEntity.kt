package com.example.stockmarket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An Entity class to store parsed data into
 * the room database.
 *
 * @author Lokik Soni
 * Created on 18/05/2022
 */
@Entity
data class StockListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)
