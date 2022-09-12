package ru.razrabs.design.components.comment

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import ru.razrabs.core.base.BaseImageLoader
import ru.razrabs.core.R
import ru.razrabs.design.subcomponents.common.VectorIconColorFilter
import ru.razrabs.design.testing.TestImageLoader
import ru.razrabs.design.theming.*
import ru.razrabs.design.unboundClickable

@Composable
fun CommentWritingBlock(
    avatarUrl: String,
    onSend: (String) -> Unit,
    imageLoader: BaseImageLoader
) {
    var comment by remember { mutableStateOf("") }
    val sendAlpha by animateFloatAsState(targetValue = if (comment.isEmpty()) 0f else 1f)

    ConstraintLayout(
        modifier = Modifier
            .background(background())
            .fillMaxWidth()
            .height(80.dp)
            .padding(16.dp)
    ) {
        val (image, tooltip, text, send) = createRefs()
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
            contentDescription = stringResource(id = R.string.your_avatar),
            modifier = Modifier
                .size(48.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        if (comment.isEmpty()) {
            Text(
                text = stringResource(id = R.string.comment_tooltip),
                style = styreneRegular(color = secondary(), size = 16),
                modifier = Modifier
                    .constrainAs(tooltip) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = 8.dp)
            )
        }
        BasicTextField(
            value = comment,
            onValueChange = { comment = it },
            textStyle = styreneRegular(color = primary(), size = 16),
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                    end.linkTo(send.start)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 8.dp)
        )
        VectorIconColorFilter(
            icon = ru.razrabs.design.R.drawable.ic_send,
            color = primary(),
            modifier = Modifier
                .unboundClickable {
                    onSend(comment)
                }
                .constrainAs(send) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .alpha(sendAlpha)
        )
    }


}

@Preview
@Composable
fun PreviewCommentWritingBlock() {
    CommentWritingBlock(
        "https://avatars.githubusercontent.com/dorjechang", {}, TestImageLoader(
            LocalContext.current
        )
    )
}