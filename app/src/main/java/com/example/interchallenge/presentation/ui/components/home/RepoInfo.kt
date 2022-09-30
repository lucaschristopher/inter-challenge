package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.theme.GoldYellow
import com.example.interchallenge.presentation.ui.theme.dp16
import com.example.interchallenge.presentation.ui.theme.dp4
import com.example.interchallenge.presentation.ui.theme.sp12

@Composable
fun RepoInfo(fraction: Float, icon: ImageVector, info: String) {
    Row(
        modifier = Modifier.fillMaxWidth(fraction = fraction),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dp4)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.star_icon_description),
            tint = GoldYellow,
            modifier = Modifier.size(dp16)
        )
        Text(
            text = info,
            color = GoldYellow,
            fontSize = sp12,
            fontWeight = FontWeight.Bold
        )
    }
}