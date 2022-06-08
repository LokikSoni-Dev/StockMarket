package com.example.stockmarket.di

import javax.inject.Qualifier

/**
 * Coroutine custom qualifier.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationScope