package ru.razrabs.design.components.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.HorizontalSpacer
import ru.razrabs.design.subcomponents.common.VectorIcon
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.styreneRegular
import ru.razrabs.design.unboundClickable

@Composable
fun CommentTopBar(commentCount: Int, onBackAction: () -> Unit, title: String) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.comment_count, commentCount),
            style = styreneRegular(color = logo(), size = 14)
        )
        HorizontalSpacer(width = 8)
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = styreneRegular(color = secondary(), size = 14)
        )
        HorizontalSpacer(width = 8)
        VectorIcon(
            icon = ru.razrabs.design.R.drawable.ic_close,
            modifier = Modifier.unboundClickable(onBackAction)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCommentTopBar() {
    CommentTopBar(
        commentCount = 2,
        onBackAction = {},
        title = "КАК Я ДЕЛАЛ ПРИНЦИПИАЛЬНО НОВЫЕ МИГРАЦИИ НА С++"
    )
}