package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.interchallenge.core.util.preview.repositoryUiModel
import com.example.interchallenge.presentation.model.RepositoryUiModel
import com.example.interchallenge.presentation.ui.components.core.Description
import com.example.interchallenge.presentation.ui.components.core.LineDivider
import com.example.interchallenge.presentation.ui.components.core.Title
import com.example.interchallenge.presentation.ui.theme.*

@Composable
fun RepoItem(
    repository: RepositoryUiModel,
    openPullRequestDetail: (String, String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openPullRequestDetail(
                    repository.owner.login,
                    repository.name
                )
            }
    ) {
        val (content, divider) = createRefs()

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp16)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (title, description, columnUser, rowInfo) = createRefs()

            Title(
                title = repository.name,
                modifier = Modifier
                    .fillMaxWidth(fraction = f05)
                    .padding(bottom = dp12)
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Description(
                description = repository.description,
                modifier = Modifier
                    .fillMaxWidth(fraction = f055)
                    .constrainAs(description) {
                        start.linkTo(parent.start)
                        top.linkTo(title.bottom)
                    }
            )

            RowInfo(
                forks = repository.forks.toString(),
                stargazers = repository.stargazers.toString(),
                modifier = Modifier
                    .padding(top = dp12)
                    .constrainAs(rowInfo) {
                        start.linkTo(parent.start)
                        top.linkTo(description.bottom)
                    }
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(f03)
                    .padding(end = dp12)
                    .constrainAs(columnUser) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                AvatarImage(
                    modifier = Modifier
                        .size(dp48)
                        .clip(CircleShape),
                    avatarUrl = repository.owner.avatarUrl
                )
                LoginUsername(
                    modifier = Modifier.padding(top = dp10, end = dp10),
                    login = repository.owner.login,
                    fontSize = sp12,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        LineDivider(modifier = Modifier
            .fillMaxWidth()
            .padding(top = dp20, bottom = dp4, start = dp16)
            .constrainAs(divider) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    InterChallengeTheme {
        RepoItem(
            repository = repositoryUiModel,
            openPullRequestDetail = { _, _ -> }
        )
    }
}

@Composable
fun AvatarEmptyImage() {
    Canvas(modifier = Modifier.size(dp40), onDraw = {
        drawCircle(color = Color.Gray)
    })
}