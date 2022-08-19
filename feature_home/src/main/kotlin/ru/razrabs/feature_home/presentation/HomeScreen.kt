package ru.razrabs.feature_home.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.components.home.HomeAppBar
import ru.razrabs.feature_feed.presentation.preview.FeedScreen

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
fun HomeContent() {
    Scaffold(topBar = {
        HomeAppBar()
    }) {
        FeedScreen(footer = { Footer() })
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent()
}