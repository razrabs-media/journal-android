package ru.razrabs.feature_feed.presentation.detail

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
import com.mukesh.MarkDown
import org.koin.androidx.compose.getViewModel
import ru.razrabs.core.ext.parseDate
import ru.razrabs.design.components.feed.BigArticle
import ru.razrabs.design.components.feed.BigArticleContent
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.secondary60
import java.net.URL

@Composable
fun DetailScreen(postUid: String, vm: DetailViewModel = getViewModel()) {
    LaunchedEffect(postUid) {
        vm.populateState(postUid)
    }
    val state = vm.state.uiState.collectAsState().value
    DetailContent(state)
}

@Composable
fun DetailContent(state: DetailViewModel.State) {
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
                MarkDown(
                    text = post.content,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetail() {

}