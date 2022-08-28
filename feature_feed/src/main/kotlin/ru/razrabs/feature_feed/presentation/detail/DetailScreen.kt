package ru.razrabs.feature_feed.presentation.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.razrabs.core.constants.POST_PREFIX
import ru.razrabs.core.ext.parseDate
import ru.razrabs.design.components.detail.PostInfoBlock
import ru.razrabs.design.components.feed.BigArticleContent
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.theming.*
import ru.razrabs.feature_feed.data.FeedCacheImpl
import ru.razrabs.feature_feed.data.FeedDataSourceImpl
import ru.razrabs.feature_feed.data.FeedRepositoryImpl
import ru.razrabs.feature_feed.domain.LoadPost
import ru.razrabs.feature_feed.presentation.detail.markdown_parsing.MarkdownItem
import ru.razrabs.feature_feed.presentation.detail.markdown_parsing.MarkdownParser
import ru.razrabs.feature_feed.presentation.detail.markdown_parsing.TextElement
import ru.razrabs.network.APIImpl

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
fun DetailContent(
    state: DetailViewModel.State,
    onCommentsClicked: (Pair<String, String>) -> Unit,
    detailImageLoader: DetailImageLoader = get()
) {
    val post = state.post
    val ctx = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(state.items) {
        state.items.filterIsInstance<MarkdownItem.Image>().forEach {
            val request = ImageRequest.Builder(ctx)
                .data(it.path)
                .build()
            detailImageLoader.loader.enqueue(request)
        }
    }
    SelectionContainer() {
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
                items(state.items) {
                    when (it) {
                        is MarkdownItem.Header -> {
                            Text(
                                text = it.text.uppercase(),
                                style = styreneMedium(color = primary(), size = 24),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                        is MarkdownItem.Image -> {
                            AsyncImage(
                                imageLoader = detailImageLoader.loader,
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.path)
                                    .crossfade(true)
                                    .memoryCacheKey(it.path)
                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .diskCacheKey(it.path)
                                    .scale(Scale.FILL)
                                    .build(),
                                contentDescription = it.contentDescription,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                            )
                        }
                        is MarkdownItem.Text -> {
                            val annotatedString = buildAnnotatedString {
                                it.elements.forEach {
                                    when (it) {
                                        is TextElement.Link -> {
                                            pushStringAnnotation(
                                                tag = it.text,
                                                annotation = it.link
                                            )
                                            withStyle(
                                                styreneRegularSpan(
                                                    color = brand(),
                                                    size = 20
                                                )
                                            ) {
                                                append(it.text)
                                            }
                                            pop()
                                        }
                                        is TextElement.UsualText -> {
                                            withStyle(
                                                styreneRegularSpan(
                                                    color = primary(),
                                                    size = 20
                                                )
                                            ) {
                                                append(it.text)
                                            }
                                        }
                                        is TextElement.Tooltip -> {
                                            withStyle(
                                                styreneRegularSpan(
                                                    color = Color.Red,
                                                    size = 20
                                                )
                                            ) {
                                                append(it.text)
                                            }
                                        }
                                    }
                                }
                            }

                            ClickableText(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                text = annotatedString,
                                style = styreneRegular(
                                    color = primary(),
                                    size = 20
                                ),
                                onClick = { offset ->
                                    it.elements.filterIsInstance<TextElement.Link>()
                                        .forEach { item ->
                                            annotatedString.getStringAnnotations(
                                                tag = item.text,
                                                start = offset,
                                                end = offset
                                            ).firstOrNull()?.let {
                                                uriHandler.openUri(item.link)
                                            }
                                        }
                                })
                        }
                        is MarkdownItem.Quotation -> {
                            Text(
                                text = it.text,
                                style = styreneRegular(color = primary(), size = 20),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .background(backgroundSecondary())
                            )
                        }
                        is MarkdownItem.Code -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                            ) {
                                it.elements.forEach {
                                    Text(
                                        text = it,
                                        style = styreneRegular(color = secondary(), size = 20)
                                    )
                                }
                            }
                        }
                        is MarkdownItem.Separator -> {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp), color = primary()
                            )
                        }
                        else -> {

                        }
                    }
                }
                item(key = "footer") {
                    Footer()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetail() {
    val vm = DetailViewModel(
        LoadPost(
            FeedRepositoryImpl(
                FeedDataSourceImpl(APIImpl()),
                FeedCacheImpl()
            )
        ),
        MarkdownParser()
    )
    LaunchedEffect(Unit) {
        vm.populateState("0b31b289-7381-4a40-84e9-278fa9aa35cc")
    }
    val state = vm.state.uiState.collectAsState().value
    DetailContent(
        state = state, onCommentsClicked = {}, detailImageLoader = DetailImageLoader(
            LocalContext.current
        )
    )
}