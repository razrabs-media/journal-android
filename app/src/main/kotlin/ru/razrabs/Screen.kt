package ru.razrabs

import ru.razrabs.feature_home.navigation.HomeScreen
import ru.razrabs.feature_home.navigation.UID

sealed class Screen(
    open val routeBody: String,
    open val suffix: String? = null,
) {
    val route: String
        get() {
            return if (routeBody.isNotBlank() && suffix != null) routeBody + suffix
            else routeBody
        }

    object HomeScreen : Screen("home")
    object CommentScreen : Screen("comment", "/{$UID}")

}

fun Screen.CommentScreen.buildRoute(
    uid: String
): String = "${routeBody}/$uid"