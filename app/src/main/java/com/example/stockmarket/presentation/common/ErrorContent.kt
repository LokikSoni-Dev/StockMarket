package com.example.stockmarket.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Display an error state using [errorContent] or main [content] state.
 *
 * @param error (state) when true, display [errorContent]
 * @param errorContent (slot) the content to display for the empty state.
 * @param content (slot) the main content to show if no [error].
 *
 * @author Lokik Soni
 * Created On 04-06-2022
 */
@Composable
fun ErrorContent(
	error: Boolean,
	errorContent: @Composable () -> Unit,
	content: @Composable () -> Unit,
) {
	if (error) {
		errorContent()
	} else {
		content()
	}
}

/**
 * Screen to show the error message.
 */
@Composable
fun ErrorScreen(
	modifier: Modifier = Modifier,
	message: String
) {
	Box(
		modifier = modifier
			.wrapContentSize(Alignment.Center),
	) {
		Text(
			text = message,
			color = MaterialTheme.colors.error
		)
	}
}