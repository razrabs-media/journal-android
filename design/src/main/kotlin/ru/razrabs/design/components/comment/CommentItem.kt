package ru.razrabs.design.components.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import ru.razrabs.design.HorizontalSpacer
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.design.theming.brand
import ru.razrabs.design.theming.primary
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.styreneRegular
import ru.razrabs.graphql.type.CommentItem

@Composable
fun CommentItem(
    username: String,
    createdAt: String,
    content: String,
    avatar: String?,
    onClick: () -> Unit
) {
    ButtonWithoutPadding(shape = cornerRadius0, onClick = onClick, elevation = zeroElevation()) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            AsyncImage(
                contentDescription = "",
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatar)
                    .diskCacheKey(avatar)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(avatar)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                onError = {
                },
                modifier = Modifier.size(40.dp)
            )
            HorizontalSpacer(width = 8)
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = username,
                        style = styreneRegular(color = secondary(), size = 14, letterSpacing = 1)
                    )
                    Text(text = createdAt, style = styreneRegular(color = secondary(), size = 14))
                }
                VerticalSpacer(height = 8)
                Text(text = content, style = styreneRegular(color = primary(), size = 16))
            }
        }
    }
}

@Preview
@Composable
fun PreviewCommentItem() {
    CommentItem(
        avatar = null,
        username = "PHILIPP RANZHIN",
        createdAt = "1 Д",
        content = "Ой как приятно видеть на своём ресурсе технические статьи, словами не передать"
    ) { }
}