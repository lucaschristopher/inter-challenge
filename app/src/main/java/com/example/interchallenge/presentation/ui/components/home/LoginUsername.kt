package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.interchallenge.presentation.model.RepositoryUiModel
import com.example.interchallenge.presentation.ui.theme.Gray
import com.example.interchallenge.presentation.ui.theme.dp10
import com.example.interchallenge.presentation.ui.theme.sp12

@Composable
fun LoginUsername(repository: RepositoryUiModel) {
    Text(
        modifier = Modifier.padding(top = dp10, end = dp10),
        text = repository.owner.login,
        fontSize = sp12,
        color = Gray,
        textAlign = TextAlign.Center,
    )
}