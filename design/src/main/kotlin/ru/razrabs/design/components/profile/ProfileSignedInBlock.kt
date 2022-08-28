package ru.razrabs.design.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import ru.razrabs.core.base.BaseImageLoader
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.theming.contrastPrimary
import ru.razrabs.design.theming.primary
import ru.razrabs.design.theming.styreneMedium
import ru.razrabs.core.R
import ru.razrabs.design.testing.TestImageLoader
import ru.razrabs.design.theming.styreneRegular

@Composable
fun ProfileSignedInBlock(
    imageLoader: BaseImageLoader,
    fullName: String,
    username: String,
    commentCount: Int,
    articleCount: Int,
    signupDate: String,
    sinceSignupDate: String,
    avatarUrl: String
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {
        AsyncImage(
            imageLoader = imageLoader.loader,
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true)
                .memoryCacheKey(avatarUrl)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(avatarUrl)
                .scale(Scale.FILL)
                .build(),
            contentDescription = fullName,
            modifier = Modifier
                .size(48.dp)
        )
        VerticalSpacer(height = 16)
        Text(text = fullName, style = styreneMedium(color = primary(), size = 24))
        VerticalSpacer(height = 8)
        Text(text = username, style = styreneMedium(color = contrastPrimary(), size = 12))
        VerticalSpacer(height = 32)
        Column {
            Text(
                text = stringResource(id = R.string.articles_written, articleCount),
                style = styreneRegular(color = primary(), size = 20)
            )
            Text(
                text = stringResource(id = R.string.comments_written, commentCount),
                style = styreneRegular(color = primary(), size = 20)
            )
            Text(
                text = stringResource(id = R.string.with_us_since, signupDate),
                style = styreneRegular(color = primary(), size = 20)
            )
            Text(
                text = sinceSignupDate,
                style = styreneMedium(color = contrastPrimary(), size = 20)
            )
        }
    }
}

data class ProfileSignedInBlockPayload(
    val fullName: String,
    val username: String,
    val commentCount: Int,
    val articleCount: Int,
    val signupDate: String,
    val sinceSignupDate: String,
    val avatarUrl: String
)

class ProfileSignedInBlockPayloadProvider :
    CollectionPreviewParameterProvider<ProfileSignedInBlockPayload>(
        listOf(
            ProfileSignedInBlockPayload(
                fullName = "LEV NAZAROV",
                username = "LEFFSU",
                commentCount = 17,
                articleCount = 4,
                signupDate = "28 августа 2022",
                sinceSignupDate = "меньше минуты",
                avatarUrl = "https://avatars.githubusercontent.com/leffsu"
            )
        )
    )

@Preview
@Composable
fun PreviewProfileSignedInBlock(@PreviewParameter(ProfileSignedInBlockPayloadProvider::class) payload: ProfileSignedInBlockPayload) {
    val ctx = LocalContext.current
    ProfileSignedInBlock(
        imageLoader = TestImageLoader(ctx),
        fullName = payload.fullName,
        username = payload.username,
        commentCount = payload.commentCount,
        articleCount = payload.articleCount,
        signupDate = payload.signupDate,
        sinceSignupDate = payload.sinceSignupDate,
        avatarUrl = payload.avatarUrl
    )
}