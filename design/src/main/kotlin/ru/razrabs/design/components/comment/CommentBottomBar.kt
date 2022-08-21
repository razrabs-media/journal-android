package ru.razrabs.design.components.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.razrabs.design.R
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.theming.*

@Composable
fun CommentBottomBar(
    onSend: (String) -> Unit,
    onLogin: () -> Unit,
    loggedIn: Boolean,
    avatar: String? = null
) {
    if (loggedIn) {

    } else {
        ButtonWithoutPadding(
            shape = cornerRadius0,
            onClick = { },
            backgroundColor = contrastSecondary(),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart){
                Text(
                    text = stringResource(id = ru.razrabs.core.R.string.login_via_github),
                    style = styreneRegular(color = primary (), size = 16),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
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
    CommentBottomBar(onSend = {}, onLogin = { }, loggedIn = payload.loggedIn)
}