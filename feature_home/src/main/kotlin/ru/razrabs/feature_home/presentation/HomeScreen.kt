package ru.razrabs.feature_home.presentation

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch
import ru.razrabs.design.components.home.Footer
import ru.razrabs.design.components.home.HomeAppBar
import ru.razrabs.design.theming.background
import ru.razrabs.feature_feed.presentation.preview.FeedScreen
import ru.razrabs.feature_home.navigation.HomeScreen
import ru.razrabs.feature_home.navigation.SetupHomeNavigation
import ru.razrabs.feature_profile.presentation.ProfileScreen

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
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ProfileScreen(onBackAction = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    })
                }
            },
            drawerState = drawerState,
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        HomeAppBar(
                            onBackAction = onBackAction,
                            backShown = showBackButton,
                            onProfileClicked = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    },
                    backgroundColor = background(),
                ) {
                    SetupHomeNavigation(
                        navController,
                        onCommentsClicked = onCommentsClicked,
                        onBackAction = onBackAction
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeContent() {}
}