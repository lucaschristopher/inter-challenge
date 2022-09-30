package com.example.interchallenge.presentation.ui.components.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.theme.DeepBlack
import com.example.interchallenge.presentation.ui.theme.dp0

@Composable
fun DetailTopBar(
    navHostController: NavHostController,
    repoName: String
) {
    TopAppBar(
        backgroundColor = DeepBlack,
        elevation = dp0,
        title = {
            Text(
                text = repoName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = if (navHostController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_description)
                    )
                }
            }
        } else {
            null
        }
    )
}