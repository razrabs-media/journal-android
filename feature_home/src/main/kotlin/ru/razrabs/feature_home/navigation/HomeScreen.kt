package ru.razrabs.feature_home.navigation

sealed class HomeScreen(
    open val routeBody: String,
    open val suffix: String? = null,
) {
    val route: String
        get() {
            return if (routeBody.isNotBlank() && suffix != null) routeBody + suffix
            else routeBody
        }

    object FeedScreen : HomeScreen("feed")
    object DetailScreen : HomeScreen("detail", "/{$UID}")
}

fun HomeScreen.DetailScreen.buildRoute(
    uid: String
): String = "${routeBody}/$uid"