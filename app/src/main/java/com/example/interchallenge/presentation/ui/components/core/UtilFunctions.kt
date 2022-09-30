package com.example.interchallenge.presentation.ui.components.core

import android.content.Context
import android.widget.Toast
import com.example.interchallenge.R

fun showToast(context: Context) {
    Toast.makeText(context, R.string.error_toast_message, Toast.LENGTH_LONG).show()
}