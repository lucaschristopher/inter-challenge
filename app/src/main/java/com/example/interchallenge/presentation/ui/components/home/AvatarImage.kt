package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.theme.GoldYellow

@Composable
fun AvatarImage(modifier: Modifier = Modifier, avatarUrl: String) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = avatarUrl,
        contentDescription = stringResource(R.string.avatar_image_description)
    ) {

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> CircularProgressIndicator(color = GoldYellow)
            is AsyncImagePainter.State.Error -> AvatarEmptyImage()
            else -> SubcomposeAsyncImageContent()
        }
    }
}