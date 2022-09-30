package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interchallenge.presentation.ui.theme.GoldYellow
import com.example.interchallenge.presentation.ui.theme.InterChallengeTheme

@Composable
fun LoadingComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = GoldYellow)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    InterChallengeTheme {
        LoadingComponent()
    }
}