package ru.razrabs.feature_home.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.components.home.HomeAppBar
import ru.razrabs.design.theming.background
import ru.razrabs.feature_feed.presentation.preview.FeedScreen
import ru.razrabs.feature_home.navigation.SetupHomeNavigation

@Composable
fun HomeScreen(onCommentsClicked: (String) -> Unit) {
    HomeContent(onCommentsClicked = onCommentsClicked)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeContent(onCommentsClicked: (String) -> Unit) {
    Scaffold(topBar = {
        HomeAppBar()
    }, backgroundColor = background()) {
        val navController = rememberAnimatedNavController()
        SetupHomeNavigation(navController, onCommentsClicked = onCommentsClicked)
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent() {}
}