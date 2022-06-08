package com.example.stockmarket.util

import com.example.stockmarket.domain.model.IntraDayInfo
import com.madrapps.plot.line.DataPoint

/**
 * Contains helper function to map list.
 *
 * @author Lokik Soni
 * Created On 31-05-2022
 */

fun List<IntraDayInfo>.toDataPointList(): List<DataPoint> {

    val list = mutableListOf<DataPoint>()
    this.forEach { item ->
        item.date?.let { date ->
            list.add(
                DataPoint(
                    x = date.hour.toFloat(),
                    y = item.close.toFloat()
                )
            )
        }
    }
    return list
}