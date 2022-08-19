package ru.razrabs.feature_feed.presentation.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.components.feed.BigArticle
import ru.razrabs.design.components.feed.SmallArticle
import ru.razrabs.design.components.feed.TextArticle
import ru.razrabs.design.components.home.ArticleGroupButton
import ru.razrabs.design.components.home.Footer

@Composable
fun FeedScreen(footer: @Composable LazyItemScope.() -> Unit) {
    FeedContent(footer = footer)
}

@Composable
fun FeedContent(
    footer: @Composable LazyItemScope.() -> Unit,
    vm: FeedViewModel = getViewModel()
) {
    LazyColumn {
        item {
            VerticalSpacer(height = 16)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                item {
                    ArticleGroupButton(title = "ВСЕ ПОДРЯД", onClick = { }, active = false)
                }
                item {
                    ArticleGroupButton(title = "ОТ РЕДАКЦИИ", onClick = { }, active = false)
                }
                item {
                    ArticleGroupButton(title = "МНЕНИЯ", onClick = { }, active = false)
                }
            }
        }
        item {
            VerticalSpacer(height = 16)
        }

        item {
            TextArticle(
                text = "«В ОБРАЗЕ ЛЕХИ МЕДЬ Я БЫЛ ПОПУЛЯРЕН СРЕДИ СИЛОВИКОВ» — АНДРЕЙ КУЗЬМИН ЗАСТРЯЛ В ЮЖНОЙ АМЕРИКЕ БЕЗ ДЕНЕГ, РАБОТЫ И ВОЗМОЖНОСТИ ВЕРНУТЬСЯ. ВОТ ЕГО ИСТОРИЯ",
                date = "2 ДНЯ НАЗАД"
            ) {

            }
        }

        item {
            BigArticle(
                title = "КАК Я ДЕЛАЛ ПРИНЦИПИАЛЬНО НОВЫЕ МИГРАЦИИ НА С++",
                url = "https://github.com/razrabs-media/editorial/raw/69d85be4e5f114837516c84d787683d3947cfd5b/sqlite-orm-new-migrations2%2Fp.jpg",
                date = "5 ЧАСОВ НАЗАД"
            ) {

            }
        }

        item {
            SmallArticle(
                title = "«МИР, В КОТОРОМ ПОЧТИ ВСЕ — АЙТИШНИКИ, НАСТУПАЕТ. НАДО БЫТЬ ГОТОВЫМ». ГЛЕБ МИХЕЕВ РАССКАЗЫВАЕТ ПРО УЧЕБУ, БУДУЩЕЕ И СОБЕСЫ В БАРАХ",
                url = "https://github.com/razrabs-media/editorial/raw/8d7686e99add998a40a8c4332fa953afff663c57/gleb_m%2Fpreview.jpg",
                date = "23 ЧАСА НАЗАД"
            ) {

            }
        }
        item {
            SmallArticle(
                title = "«В ОБРАЗЕ ЛЕХИ МЕДЬ Я БЫЛ ПОПУЛЯРЕН СРЕДИ СИЛОВИКОВ» — АНДРЕЙ КУЗЬМИН ЗАСТРЯЛ В ЮЖНОЙ АМЕРИКЕ БЕЗ ДЕНЕГ, РАБОТЫ И ВОЗМОЖНОСТИ ВЕРНУТЬСЯ. ВОТ ЕГО ИСТОРИЯ",
                url = "https://github.com/razrabs-media/editorial/raw/70cc7632cae165f1e09651863252c6f087550973/alex-cuprum-story%2Fpreview.jpg",
                date = "3 ДНЯ НАЗАД"
            ) {

            }
        }
        item {
            SmallArticle(
                title = "АЙТИ СООБЩЕСТВО ВИДИТ ТОЛЬКО ТЕХНИЧЕСКИЕ ПРОБЛЕМЫ И ИГНОРИРУЕТ ЧЕЛОВЕЧЕСКИЕ. ЭТО ЕГО УБЬЕТ",
                url = "https://github.com/razrabs-media/editorial/raw/810fc5b983d4bb6ba1194d45a2cad64cb6dc0341/tech-vs-life%2Fpreview.jpg",
                date = "9 АВГУСТА"
            ) {

            }
        }
        item {
            BigArticle(
                title = "БОЛЬШОЙ ТОЛСТЫЙ АПДЕЙТ",
                url = "https://github.com/razrabs-media/editorial/raw/0e09321ddb3eadf6a279c5bbc0960b8f81dc277c/release_1_1%2Fpreview.jpg",
                date = "3 ДНЯ НАЗАД"
            ) {

            }
        }
        item {
            SmallArticle(
                title = "РАЗРАБ — КТО ТЫ, ЧТО С ТОБОЙ СТАЛО, ЧТО С ТОБОЙ БУДЕТ",
                url = "https://github.com/razrabs-media/editorial/raw/6ebe0f04b43fed5baf14c9a5cbc94ddc717763de/who-we-are%2Fpreview.jpg",
                date = "1 АВГУСТА"
            ) {

            }
        }

        item {
            footer()
        }
    }
}

@Preview
@Composable
fun PreviewFeed() {
    FeedContent(footer = { Footer() })
}