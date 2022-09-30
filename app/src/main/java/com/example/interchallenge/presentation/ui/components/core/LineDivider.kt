package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.interchallenge.presentation.ui.theme.af02
import com.example.interchallenge.presentation.ui.theme.dp1

@Composable
fun LineDivider(modifier: Modifier = Modifier) {
    Divider(
        color = Color.Gray.copy(af02),
        thickness = dp1,
        modifier = modifier
    )
}