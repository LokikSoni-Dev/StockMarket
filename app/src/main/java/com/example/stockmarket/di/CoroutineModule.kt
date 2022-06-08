package com.example.stockmarket.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides app level dependencies
 * related to the coroutine.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher() = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}