package ru.razrabs.network.models.user

import ru.razrabs.graphql.CurrentUserQuery

data class CurrentUser(
    val login: String,
    val commentsCount: Double,
    val postsCount: Double,
    val profile: Profile,
)

data class Profile(
    val avatarUrl: String?,
    val createdAt: Any,
    val fullName: String?,
)

fun CurrentUserQuery.CurrentUser.map(profile: CurrentUserQuery.Profile) =
    CurrentUser(
        login = login,
        commentsCount = commentsCount,
        postsCount = postsCount,
        profile = Profile(
            avatarUrl = profile.avatarUrl,
            createdAt = profile.createdAt,
            fullName = profile.fullName
        )
    )