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
import ru.razrabs.design.components.feed.TextArticle
import ru.razrabs.design.components.home.ArticleGroupButton
import ru.razrabs.design.components.home.Footer

@Composable
fun FeedScreen(footer: @Composable LazyItemScope.() -> Unit,) {
    FeedContent(footer = footer)
}

@Composable
fun FeedContent(
    footer: @Composable LazyItemScope.() -> Unit,
    vm: FeedViewModel = getViewModel()
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
                title = "КИРА 2PIZZA БРОСИЛ РАБОТУ И ТЕПЕРЬ ДЕЛАЕТ БЕСПЛАТНЫЙ VPN FUCKRKN1. А ЕЩЕ МНОГО, ОЧЕНЬ МНОГО ШИТПОСТИТ — ЗАЧЕМ ЭТО ВСЕ?",
                url = "https://github.com/razrabs-media/editorial/raw/8e6593753adc95858b8791a99d7f78df4def83a2/2pizza-story%2Fpreview.jpg",
                date = "6 ДНЕЙ НАЗАД"
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
    FeedContent(footer = { Footer()})
}