package ru.razrabs.feature_auth.domain

import org.koin.core.annotation.Single

@Single
class SignOut(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.signOut()
}