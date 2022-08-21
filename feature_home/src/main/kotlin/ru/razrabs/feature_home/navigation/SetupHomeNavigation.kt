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
import ru.razrabs.feature_comments.presentation.CommentScreen
import ru.razrabs.feature_feed.presentation.detail.DetailScreen
import ru.razrabs.feature_feed.presentation.preview.FeedScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupHomeNavigation(navController: NavHostController, onCommentsClicked: (String) -> Unit) {
    AnimatedNavHost(navController = navController, startDestination = HomeScreen.FeedScreen.route) {
        composable(HomeScreen.FeedScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            FeedScreen(
                footer = { Footer() },
                openDetail = { navController.navigate(HomeScreen.DetailScreen.buildRoute(it)) })
        }
        composable(HomeScreen.DetailScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            it.arguments?.getString(UID)?.let { postUid ->
                DetailScreen(postUid = postUid, onCommentsClicked = {
                    onCommentsClicked(postUid)
                })
            }
        }
    }
}