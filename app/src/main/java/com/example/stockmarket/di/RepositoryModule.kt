package com.example.stockmarket.di

import com.example.stockmarket.data.csv.CSVParser
import com.example.stockmarket.data.csv.IntraDayInfoParser
import com.example.stockmarket.data.csv.StockListingsParser
import com.example.stockmarket.data.repository.StockRepositoryImpl
import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.domain.model.StockListing
import com.example.stockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides app level abstract dependencies
 * related to the repository.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provides object [CSVParser] of type [StockListing]
     * by using implementation of [StockListingsParser].
     */
    @Binds
    @Singleton
    abstract fun bindStockListingsParser(
        stockListingsParser: StockListingsParser
    ): CSVParser<StockListing>

    /**
     * Provides object [CSVParser] of type [IntraDayInfo]
     * by using implementation of [IntraDayInfoParser].
     */
    @Binds
    @Singleton
    abstract fun bindIntraDayInfoParser(
        intraDayInfoParser: IntraDayInfoParser
    ): CSVParser<IntraDayInfo>


    /**
     * Provides object [StockRepository] by using
     * implementation of [StockRepositoryImpl].
     */
    @Binds
    @Singleton
    abstract fun bindStockRepositoryIml(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}