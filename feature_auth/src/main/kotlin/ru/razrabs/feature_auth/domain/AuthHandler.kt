package ru.razrabs.feature_auth.domain

import android.app.Activity

interface AuthHandler {
    suspend fun auth(activity: Activity): ru.razrabs.core.ext.Result<User, Exception>

    suspend fun getUser(): UserWithoutInfo?

    suspend fun signOut()
}