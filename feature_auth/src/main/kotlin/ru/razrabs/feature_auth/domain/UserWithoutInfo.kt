package ru.razrabs.feature_auth.domain

data class UserWithoutInfo(
    val uid: String,
    val name: String,
    val avatarUrl: String
)