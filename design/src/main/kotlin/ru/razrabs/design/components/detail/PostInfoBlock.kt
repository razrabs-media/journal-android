package ru.razrabs.design.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.components.home.ArticleGroupButton
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.core.R
import ru.razrabs.design.theming.*

@Composable
fun PostInfoBlock(
    tags: List<String>,
    commentCount: Int,
    onCommentsClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        VerticalSpacer(height = 8)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), color = logo()
        )
        VerticalSpacer(height = 8)
        if (tags.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tags) {
                    ArticleGroupButton(title = it, onClick = { }, active = false, clickable = false)
                }
            }
            VerticalSpacer(height = 8)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            PostInfoBlockButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.comment_count, commentCount),
                onClick = onCommentsClicked
            )
            PostInfoBlockButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.share),
                onClick = onShareClicked
            )
        }
        VerticalSpacer(height = 32)
    }
}

@Composable
fun PostInfoBlockButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        backgroundColor = backgroundSecondary(),
        modifier = modifier,
        elevation = zeroElevation()
    ) {
        Text(
            text = text,
            style = styreneRegular(color = secondary(), size = 14),
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPostInfoBlock() {
    PostInfoBlock(
        tags = listOf("Test tag1", "test tag 2"),
        commentCount = 7,
        onCommentsClicked = {},
        onShareClicked = {})
}