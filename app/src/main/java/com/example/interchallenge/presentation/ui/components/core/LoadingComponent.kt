package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.interchallenge.core.constants.LOADING_COMPONENT_TAG
import com.example.interchallenge.presentation.ui.theme.GoldYellow
import com.example.interchallenge.presentation.ui.theme.InterChallengeTheme

@Composable
fun LoadingComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag(LOADING_COMPONENT_TAG),
            color = GoldYellow
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    InterChallengeTheme {
        LoadingComponent()
    }
}