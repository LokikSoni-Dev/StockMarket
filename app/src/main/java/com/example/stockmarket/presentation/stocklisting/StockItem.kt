package com.example.stockmarket.presentation.stocklisting

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.domain.model.StockListing

/**
 * An Item used to show the stocks in [StockListingsScreen].
 *
 * @author Lokik Soni
 * Created On 19-05-2022
 */
@Composable
fun StockItem(
    modifier: Modifier = Modifier,
    stockListing: StockListing
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stockListing.name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = stockListing.exchange,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onBackground,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "(${stockListing.symbol})",
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.onBackground,
        )
    }
}