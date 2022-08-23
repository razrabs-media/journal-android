package ru.razrabs

import ru.razrabs.feature_home.navigation.ARTICLE_NAME
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
    object CommentScreen : Screen("comment", "/{$UID}/{$ARTICLE_NAME}")

}

fun Screen.CommentScreen.buildRoute(
    uid: String,
    name: String
): String = "${routeBody}/$uid/$name"