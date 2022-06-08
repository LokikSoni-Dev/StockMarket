package com.example.stockmarket.presentation.stockinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.presentation.common.ErrorContent
import com.example.stockmarket.presentation.common.ErrorScreen
import com.example.stockmarket.presentation.common.FullScreenLoading
import com.example.stockmarket.ui.theme.DarkBlue
import com.example.stockmarket.util.rememberFlowWithLifecycle

/**
 * Shows the stock info with chart.
 *
 * @author Lokik Soni
 * Created On 27-05-2022
 */
@Composable
fun StockInfoScreen(
    modifier: Modifier = Modifier,
//    symbol: String?,
    stockInfoViewModel: StockInfoViewModel,
) {
    val stockInfoState by rememberFlowWithLifecycle(
        stockInfoViewModel.stockInfoState
    ).collectAsState(StockInfoState())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue),
    ) {
        ErrorContent(
            error = stockInfoState.stockInfo == null && !stockInfoState.isLoading,
            errorContent = {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    message = stockInfoState.errMsgStockInfo ?: "Something went wrong."
                )
            }
        ) {
            stockInfoState.stockInfo?.let { stockInfo ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stockInfo.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stockInfo.symbol,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Industry: ${stockInfo.industry}",
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Country: ${stockInfo.country}",
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stockInfo.description,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        ErrorContent(
            error = stockInfoState.intraDayInfo.isEmpty() && !stockInfoState.isLoading,
            errorContent = {
                ErrorScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    message = stockInfoState.errMsgIntraDayInfo ?: "Something went wrong."
                )
            }
        ) {
            if (stockInfoState.intraDayInfo.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                StockChart(
                    modifier = Modifier.weight(1.5f),
                    infos = stockInfoState.intraDayInfo
                )
            }
        }
    }

    if (stockInfoState.isLoading) {
        FullScreenLoading()
    }
}