package ru.razrabs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.razrabs.feature_comments.presentation.CommentScreen
import ru.razrabs.feature_home.navigation.ARTICLE_NAME
import ru.razrabs.feature_home.navigation.UID
import ru.razrabs.feature_home.presentation.HomeScreen

@Composable
fun SetupMainNavigation(
    navController: NavHostController,
    finishRootScreenAction: () -> Unit,
) {

    fun goToHome() {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(0)
        }
    }

    val onBackAction = { if (!navController.navigateUp()) finishRootScreenAction() }

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(onCommentsClicked = {
                navController.navigate(Screen.CommentScreen.buildRoute(it.second, it.first))
            })
        }

        composable(Screen.CommentScreen.route) {
            val uid = it.arguments?.getString(UID)
            val name = it.arguments?.getString(ARTICLE_NAME)
            if (uid != null && name != null) {
                CommentScreen(onBackAction = onBackAction, postUid = uid, name = name)
            }
        }
    }
}