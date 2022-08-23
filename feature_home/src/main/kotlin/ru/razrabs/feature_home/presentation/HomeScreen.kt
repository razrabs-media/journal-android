package ru.razrabs.feature_home.presentation

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.components.home.HomeAppBar
import ru.razrabs.design.theming.background
import ru.razrabs.feature_feed.presentation.preview.FeedScreen
import ru.razrabs.feature_home.navigation.HomeScreen
import ru.razrabs.feature_home.navigation.SetupHomeNavigation

@Composable
fun HomeScreen(onCommentsClicked: (Pair<String, String>) -> Unit) {
    HomeContent(onCommentsClicked = onCommentsClicked)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeContent(onCommentsClicked: (Pair<String, String>) -> Unit) {
    val navController = rememberAnimatedNavController()
    val onBackAction = {
        navController.navigateUp()
        Unit
    }
    var showBackButton by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        showBackButton = destination.route != HomeScreen.FeedScreen.route
    }
    Scaffold(topBar = {
        HomeAppBar(
            onBackAction = onBackAction,
            backShown = showBackButton
        )
    }, backgroundColor = background()) {
        SetupHomeNavigation(
            navController,
            onCommentsClicked = onCommentsClicked,
            onBackAction = onBackAction
        )
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent() {}
}