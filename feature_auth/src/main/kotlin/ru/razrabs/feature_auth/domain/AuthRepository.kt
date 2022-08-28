package ru.razrabs.feature_auth.domain

import android.app.Activity

interface AuthRepository {
    suspend fun auth(activity: Activity): ru.razrabs.core.ext.Result<User, Exception>
    suspend fun getUser(): User?
    suspend fun signOut()
}