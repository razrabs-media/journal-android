package ru.razrabs.feature_auth.domain

import org.koin.core.annotation.Single

@Single
class GetUserFlow(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.getUserFlow()
}