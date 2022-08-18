package ru.razrabs.feature_home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.components.home.ArticleGroupButton
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.components.home.HomeAppBar

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
    Scaffold(topBar = {
        HomeAppBar()
    }) {
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
                Footer()
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent()
}