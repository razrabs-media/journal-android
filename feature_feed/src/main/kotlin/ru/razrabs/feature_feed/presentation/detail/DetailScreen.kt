package ru.razrabs.feature_feed.presentation.detail

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import com.mukesh.MarkDown
import org.koin.androidx.compose.getViewModel
import ru.razrabs.core.constants.POST_PREFIX
import ru.razrabs.core.ext.parseDate
import ru.razrabs.design.components.detail.PostInfoBlock
import ru.razrabs.design.components.feed.BigArticle
import ru.razrabs.design.components.feed.BigArticleContent
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.secondary60
import ru.razrabs.design.theming.styreneRegular
import java.net.URL

@Composable
fun DetailScreen(
    postUid: String,
    vm: DetailViewModel = getViewModel(),
    onCommentsClicked: (Pair<String, String>) -> Unit
) {
    LaunchedEffect(postUid) {
        vm.populateState(postUid)
    }
    val state = vm.state.uiState.collectAsState().value
    DetailContent(state, onCommentsClicked = onCommentsClicked)
}

fun Context.shareLink(link: String) {
    ShareCompat.IntentBuilder(this)
        .setType("text/plain")
        .setChooserTitle("поделиться куда")
        .setText(link)
        .startChooser()
}

@Composable
fun DetailContent(state: DetailViewModel.State, onCommentsClicked: (Pair<String, String>) -> Unit) {
    val post = state.post
    val ctx = LocalContext.current

    if (post != null) {
        LazyColumn() {
            item {
                BigArticleContent(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    title = post.title,
                    url = post.previewUrl ?: "",
                    date = ctx.parseDate(post.createdAt)
                )
            }
            item {
                Text(
                    text = post.description,
                    style = styreneRegular(color = secondary(), size = 24),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            item {
                PostInfoBlock(
                    tags = post.tags?.map { it.name } ?: listOf(),
                    commentCount = post.comments.size,
                    onCommentsClicked = { onCommentsClicked(Pair(post.title, post.uid)) },
                    onShareClicked = {
                        ctx.shareLink(link = "$POST_PREFIX${post.uid}")
                    })
            }
            item(key = "markdown") {
                MarkDown(
                    text = post.content, modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            item(key = "footer") {
                Footer()
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetail() {

}