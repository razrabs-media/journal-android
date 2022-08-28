package ru.razrabs.feature_auth.domain

import android.app.Activity
import org.koin.core.annotation.Single

@Single
class SignIn(private val authRepository: AuthRepository) {
    suspend operator fun invoke(activity: Activity) = authRepository.auth(activity)
}