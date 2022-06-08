package com.example.stockmarket

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Entry point of the application.
 * We have annotated it with [@HiltAndroidApp]
 * to tell hilt this is our application class
 * that is from where hilt will get application context.
 * So if we have any dependency which need application context
 * then hilt will actually know where to get it from.
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@HiltAndroidApp
class StockApplication: Application()