package ru.razrabs.feature_home.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ru.razrabs.design.components.home.Footer
import ru.razrabs.feature_feed.presentation.detail.DetailScreen
import ru.razrabs.feature_feed.presentation.preview.FeedScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupHomeNavigation(navController: NavHostController) {
    AnimatedNavHost(navController = navController, startDestination = HomeScreen.FeedScreen.route) {
        composable(HomeScreen.FeedScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.DetailScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    HomeScreen.DetailScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.DetailScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    HomeScreen.DetailScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            }) {
            FeedScreen(
                footer = { Footer() },
                openDetail = { navController.navigate(HomeScreen.DetailScreen.buildRoute(it)) })
        }
        composable(HomeScreen.DetailScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.FeedScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    HomeScreen.FeedScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.FeedScreen.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    HomeScreen.FeedScreen.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            }) {
            it.arguments?.getString(UID)?.let {
                DetailScreen(postUid = it)
            }
        }
    }
}