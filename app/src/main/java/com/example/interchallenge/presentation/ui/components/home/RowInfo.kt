package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.interchallenge.presentation.ui.theme.f02
import com.example.interchallenge.presentation.ui.theme.f03

@Composable
fun RowInfo(forks: String, stargazers: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        // Forks
        RepoInfo(
            fraction = f02,
            icon = Icons.Filled.Share,
            forks
        )

        // Stars
        RepoInfo(
            fraction = f03,
            icon = Icons.Filled.Star,
            stargazers
        )
    }
}