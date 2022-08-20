package ru.razrabs.feature_feed.presentation.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel
import ru.razrabs.core.ext.parseDate
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.components.feed.BigArticle
import ru.razrabs.design.components.feed.TextArticle
import ru.razrabs.design.components.home.ArticleGroupButton
import ru.razrabs.design.components.home.Footer

@Composable
fun FeedScreen(
    footer: @Composable LazyItemScope.() -> Unit,
    vm: FeedViewModel = getViewModel(),
    openDetail: (String) -> Unit
) {
    val state = vm.state.uiState.collectAsState().value
    FeedContent(
        footer = footer,
        state = state,
        onForceUpdate = { vm.updateFrontPage(true) },
        openDetail = openDetail
    )
}

@Composable
fun FeedContent(
    footer: @Composable LazyItemScope.() -> Unit,
    state: FeedViewModel.State,
    onForceUpdate: () -> Unit,
    openDetail: (String) -> Unit
) {
    val ctx = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onForceUpdate,
    ) {
        LazyColumn() {
            item {
                VerticalSpacer(height = 16)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    item {
                        ArticleGroupButton(title = "ВСЕ ПОДРЯД", onClick = { }, active = false, clickable = true)
                    }
                    item {
                        ArticleGroupButton(title = "ОТ РЕДАКЦИИ", onClick = { }, active = false, clickable = true)
                    }
                    item {
                        ArticleGroupButton(title = "МНЕНИЯ", onClick = { }, active = false, clickable = true)
                    }
                }
            }

            items(state.frontPage?.content ?: listOf(), key = { it.uid }) {
                BigArticle(
                    modifier = Modifier.padding(top = 16.dp),
                    title = it.post.title,
                    url = it.post.previewUrl ?: "",
                    date = ctx.parseDate(it.post.createdAt)
                ) {
                    openDetail(it.post.uid)
                }
            }

//        item {
//            TextArticle(
//                text = "«В ОБРАЗЕ ЛЕХИ МЕДЬ Я БЫЛ ПОПУЛЯРЕН СРЕДИ СИЛОВИКОВ» — АНДРЕЙ КУЗЬМИН ЗАСТРЯЛ В ЮЖНОЙ АМЕРИКЕ БЕЗ ДЕНЕГ, РАБОТЫ И ВОЗМОЖНОСТИ ВЕРНУТЬСЯ. ВОТ ЕГО ИСТОРИЯ",
//                date = "2 ДНЯ НАЗАД"
//            ) {
//
//            }
//        }
//
//        item {
//            BigArticle(
//                title = "КИРА 2PIZZA БРОСИЛ РАБОТУ И ТЕПЕРЬ ДЕЛАЕТ БЕСПЛАТНЫЙ VPN FUCKRKN1. А ЕЩЕ МНОГО, ОЧЕНЬ МНОГО ШИТПОСТИТ — ЗАЧЕМ ЭТО ВСЕ?",
//                url = "https://github.com/razrabs-media/editorial/raw/8e6593753adc95858b8791a99d7f78df4def83a2/2pizza-story%2Fpreview.jpg",
//                date = "6 ДНЕЙ НАЗАД"
//            ) {
//
//            }
//        }

            item {
                footer()
            }
        }
    }

}

@Preview
@Composable
fun PreviewFeed() {
    FeedContent(
        footer = { Footer() },
        state = FeedViewModel.State(),
        onForceUpdate = {},
        openDetail = {})
}