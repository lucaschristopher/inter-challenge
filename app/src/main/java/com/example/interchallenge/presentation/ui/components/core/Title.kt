package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.interchallenge.core.constants.TITLE_MAX_LINE
import com.example.interchallenge.presentation.ui.theme.DarkBlue
import com.example.interchallenge.presentation.ui.theme.sp18

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        maxLines = TITLE_MAX_LINE,
        overflow = TextOverflow.Ellipsis,
        text = title,
        color = DarkBlue,
        fontSize = sp18
    )
}