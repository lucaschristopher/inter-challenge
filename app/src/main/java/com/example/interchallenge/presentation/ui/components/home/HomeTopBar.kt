package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.theme.DeepBlack
import com.example.interchallenge.presentation.ui.theme.dp0

@Composable
fun HomeTopBar() {
    TopAppBar(
        backgroundColor = DeepBlack,
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        },
        elevation = dp0
    )
}