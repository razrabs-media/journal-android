package ru.razrabs.design.components.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.razrabs.core.base.BaseImageLoader
import ru.razrabs.design.R
import ru.razrabs.design.components.common.LoginViaGithubButton
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.testing.TestImageLoader
import ru.razrabs.design.theming.*

@Composable
fun CommentBottomBar(
    onSend: (String) -> Unit,
    onLogin: () -> Unit,
    loggedIn: Boolean,
    avatar: String? = null,
    imageLoader: BaseImageLoader
) {
    if (loggedIn) {
        CommentWritingBlock(avatarUrl = avatar ?: "", onSend = onSend, imageLoader = imageLoader)
    } else {
        LoginViaGithubButton(onClick = onLogin)
    }
}

data class CommentBottomBarPayload(val loggedIn: Boolean)

class CommentBottomBarPayloadProvider : CollectionPreviewParameterProvider<CommentBottomBarPayload>(
    listOf(
        CommentBottomBarPayload(true),
        CommentBottomBarPayload(false),
    )
)

@Preview(showSystemUi = true)
@Composable
fun PreviewCommentBottomBar(@PreviewParameter(CommentBottomBarPayloadProvider::class) payload: CommentBottomBarPayload) {
    val ctx = LocalContext.current
    CommentBottomBar(
        onSend = {},
        onLogin = { },
        loggedIn = payload.loggedIn,
        imageLoader = TestImageLoader(ctx)
    )
}