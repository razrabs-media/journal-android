package ru.razrabs.feature_auth.domain

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun auth(activity: Activity): ru.razrabs.core.ext.Result<User, Exception>
    suspend fun getUser(): User?

    fun getUserFlow(): Flow<User?>
    suspend fun signOut()
}