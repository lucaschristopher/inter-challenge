package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.interchallenge.presentation.ui.theme.Gray

@Composable
fun LoginUsername(
    modifier: Modifier = Modifier,
    login: String,
    fontSize: TextUnit,
    fontWeight: FontWeight
) {
    Text(
        modifier = modifier,
        text = login,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = Gray,
        textAlign = TextAlign.Center,
    )
}