package com.example.interchallenge.presentation.ui.fragments.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat.startActivity
import com.example.interchallenge.presentation.model.PullRequestUiModel
import com.example.interchallenge.presentation.ui.components.core.Description
import com.example.interchallenge.presentation.ui.components.core.LineDivider
import com.example.interchallenge.presentation.ui.components.core.Title
import com.example.interchallenge.presentation.ui.components.home.AvatarImage
import com.example.interchallenge.presentation.ui.components.home.LoginUsername
import com.example.interchallenge.presentation.ui.theme.*

@Composable
fun DetailScreen(data: List<PullRequestUiModel>) {
//    TODO: Remove?
//    Box(
//        contentAlignment = Alignment.CenterStart,
//        modifier = Modifier
//            .background(GrayAccent)
//            .fillMaxWidth()
//            .fillMaxHeight(f01)
//    ) {
//        Text(
//            text = "16 opened",
//            color = GoldYellow,
//            fontSize = sp16,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(dp16)
//        )
//    }

    // LIST OF PR's
    LazyColumn {
        items(items = data) { item ->
            CardContent(item)
        }
    }
}

@Composable
fun CardContent(item: PullRequestUiModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { linkToWebPage(context, item) }
    ) {
        Column(modifier = Modifier.padding(dp16)) {
            Title(title = item.title)
            Spacer(Modifier.size(dp2))
            Description(description = item.body)
        }

        Row(
            modifier = Modifier.padding(start = dp16)
        ) {
            AvatarImage(
                avatarUrl = item.user.avatarUrl,
                modifier = Modifier
                    .size(dp48)
                    .clip(CircleShape),
            )
            LoginUsername(
                modifier = Modifier.padding(start = dp16, top = dp10),
                login = item.user.login,
                fontSize = sp16,
                fontWeight = FontWeight.Bold
            )
        }

        LineDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp20, bottom = dp4, start = dp16)
        )
    }
}

fun linkToWebPage(context: Context, item: PullRequestUiModel) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(item.url)
    startActivity(context, openURL, null)
}