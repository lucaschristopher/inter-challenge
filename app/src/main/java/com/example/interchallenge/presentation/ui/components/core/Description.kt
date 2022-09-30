package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.interchallenge.core.constants.DESCRIPTION_MAX_LINE
import com.example.interchallenge.presentation.ui.theme.Gray
import com.example.interchallenge.presentation.ui.theme.sp12

@Composable
fun Description(description: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        maxLines = DESCRIPTION_MAX_LINE,
        overflow = TextOverflow.Ellipsis,
        text = description,
        color = Gray,
        fontSize = sp12,
        fontWeight = FontWeight.Bold
    )
}