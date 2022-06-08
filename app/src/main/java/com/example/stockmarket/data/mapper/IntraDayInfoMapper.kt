package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.remote.dto.IntraDayInfoDto
import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.util.DateUtils.serverDateToDate

/**
 * Map the remote Dto to the data used for UI.
 *
 * @author Lokik Soni
 * Created On 23-05-2022
 */
fun IntraDayInfoDto.toIntraDayInfo() = IntraDayInfo(
    date = timeStamp.serverDateToDate(),
    close = close
)