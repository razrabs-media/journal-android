package ru.razrabs.feature_auth.domain

import org.koin.core.annotation.Single
import ru.razrabs.feature_auth.domain.GetUser
import ru.razrabs.feature_auth.domain.SignIn
import ru.razrabs.feature_auth.domain.SignOut

@Single
class AuthGate(
    val getUser: GetUser,
    val signIn: SignIn,
    val signOut: SignOut
) {
}