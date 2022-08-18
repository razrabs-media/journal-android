package ru.razrabs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
//        startDestination = Screen.CommunityChooseUsername.route
    ) {
        composable(Screen.HomeScreen.route){

        }
    }
}