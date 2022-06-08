package com.example.stockmarket.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

/**
 * Helper class to format date and time.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
object DateUtils {

    /**
     * All formats that will be used in app.
     */
    enum class DateFormats(val value: String) {
        SERVER_DEFAULT_FORMAT("yyyy-MM-dd HH:mm:ss");
    }

    private fun getFormatter(format: DateFormats, locale: Locale = Locale.getDefault()) =
        DateTimeFormatter.ofPattern(format.value, locale)

    /**
     * Converts a string into a [Date] object if string is in
     * [DateFormats.SERVER_DEFAULT_FORMAT] else return null.
     */
    fun String.serverDateToDate(locale: Locale = Locale.getDefault()) =  try {
        LocalDateTime.parse(
            this,
            getFormatter(DateFormats.SERVER_DEFAULT_FORMAT, locale)
        )
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        null
    }
}

