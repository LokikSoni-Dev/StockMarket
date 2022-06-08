package com.example.stockmarket.presentation.stockinfo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stockmarket.domain.model.IntraDayInfo
import com.example.stockmarket.ui.theme.DarkBlue
import com.example.stockmarket.util.toDataPointList
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

/**
 * Plot a graph using [infos] list.
 *
 * @author Lokik Soni
 * Created On 27-05-2022
 */
@Composable
fun StockChart(
    modifier: Modifier = Modifier,
    infos: List<IntraDayInfo> = emptyList(),
) {
    LineGraph(
        modifier = modifier,
        plot = LinePlot(
            lines = listOf(
                LinePlot.Line(
                    infos.toDataPointList(),
                    LinePlot.Connection(color = Color.Green),
                    LinePlot.Intersection(color = Color.White),
                    LinePlot.Highlight(color = DarkBlue),
                )
            ),
            grid = LinePlot.Grid(Color.Cyan, steps = 4)
        )
    )
}