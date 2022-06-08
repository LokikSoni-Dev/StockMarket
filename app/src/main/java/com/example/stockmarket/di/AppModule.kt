package com.example.stockmarket.di

import android.app.Application
import androidx.room.Room
import com.example.stockmarket.BuildConfig
import com.example.stockmarket.data.local.StockDatabase
import com.example.stockmarket.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Provides app level singleton dependencies.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockMarketDatabase(application: Application): StockDatabase =
        Room.databaseBuilder(
            application,
            StockDatabase::class.java,
            StockDatabase.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideStockDao(stockDatabase: StockDatabase) = stockDatabase.dao

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(okhttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) okHttpClient.addInterceptor(okhttpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideStockApi(okHttpClient: OkHttpClient): StockApi =
        Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
}