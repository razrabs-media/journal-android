package ru.razrabs.feature_auth.data

import com.google.firebase.auth.FirebaseUser
import ru.razrabs.feature_auth.domain.User
import ru.razrabs.feature_auth.domain.UserWithoutInfo


fun FirebaseUser.parseUser(username: String?) = User(
    uid = uid,
    name = displayName ?: "",
    usernameUrl = username?.let { "https://github.com/$it" } ?: "",
    avatarUrl = photoUrl.toString()
)

fun FirebaseUser.parseUserWithoutInfo() = UserWithoutInfo(
    uid = uid,
    name = displayName ?: "",
    avatarUrl = photoUrl.toString()
)

fun UserWithoutInfo.map(usernameUrl: String?) = User(
    uid = uid,
    name = name,
    avatarUrl = avatarUrl,
    usernameUrl = usernameUrl
)