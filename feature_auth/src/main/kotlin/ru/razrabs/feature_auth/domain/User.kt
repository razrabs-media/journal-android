package ru.razrabs.feature_auth.domain

data class User(
    val uid: String,
    val name: String,
    val usernameUrl: String?,
    val avatarUrl: String,
    val username: String?
)