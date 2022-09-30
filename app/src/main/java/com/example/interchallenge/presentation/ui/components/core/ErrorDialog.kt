package com.example.interchallenge.presentation.ui.components.core

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.theme.DarkBlue
import com.example.interchallenge.presentation.ui.theme.InterChallengeTheme

@Composable
fun ErrorDialog(
    onClick: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(R.string.error_dialog_title))
            },
            text = {
                Text(text = stringResource(R.string.error_dialog_default_description))
            },
            confirmButton = {
                TextButton(onClick = {
                    onClick()
                    openDialog.value = false
                }) {
                    Text(
                        text = stringResource(id = R.string.reload_label).uppercase(),
                        color = DarkBlue
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    InterChallengeTheme {
        ErrorDialog()
    }
}