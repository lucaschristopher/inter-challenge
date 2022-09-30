package com.example.interchallenge.presentation.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.interchallenge.R
import com.example.interchallenge.core.constants.DESCRIPTION_MAX_LINE
import com.example.interchallenge.core.constants.TITLE_MAX_LINE
import com.example.interchallenge.core.constants.util.preview.repositoryUiModel
import com.example.interchallenge.presentation.model.RepositoryUiModel
import com.example.interchallenge.presentation.ui.theme.*

@Composable
fun RepoItem(
    repository: RepositoryUiModel,
    navHostController: NavHostController
) {
    // All content
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
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
                },
        ) {

            val (title, description, columnUser) = createRefs()

            // Repo title
            Text(
                modifier = Modifier
                    .fillMaxWidth(fraction = f05)
                    .padding(bottom = dp12)
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                maxLines = TITLE_MAX_LINE,
                overflow = TextOverflow.Ellipsis,
                text = repository.name,
                color = DarkBlue,
                fontSize = sp18
            )

            // Repo description
            Text(
                modifier = Modifier
                    .fillMaxWidth(fraction = f055)
                    .constrainAs(description) {
                        start.linkTo(parent.start)
                        top.linkTo(title.bottom)
                    },
                maxLines = DESCRIPTION_MAX_LINE,
                overflow = TextOverflow.Ellipsis,
                text = repository.description,
                color = Gray,
                fontSize = sp12,
                fontWeight = FontWeight.Bold
            )

            // User data
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(f03)
                    .padding(end = dp12)
                    .constrainAs(columnUser) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            ) {
                // Avatar URL
                AvatarImage(repository)
                // Login user
                LoginUsername(repository)
            }
        }
        // DIVIDER
        Divider(
            color = Color.Gray.copy(af02),
            thickness = dp1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp20, bottom = dp4, start = dp16)
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    InterChallengeTheme {
        RepoItem(
            repository = repositoryUiModel,
            navHostController = rememberNavController()
        )
    }
}

@Composable
fun AvatarEmptyImage() {
    Canvas(modifier = Modifier.size(dp40), onDraw = {
        drawCircle(color = Color.Gray)
    })
}