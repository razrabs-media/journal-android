package ru.razrabs.feature_auth.data

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthProvider
import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.feature_auth.domain.AuthHandler
import ru.razrabs.feature_auth.domain.User
import ru.razrabs.feature_auth.domain.UserWithoutInfo
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@Single
class GitHubAuthHandlerImpl : AuthHandler {

    private val provider = OAuthProvider.newBuilder("github.com")
    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun auth(activity: Activity): ru.razrabs.core.ext.Result<User, Exception> =
        suspendCoroutine { cont ->
            firebaseAuth
                .startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { result ->
                    val user = result.user?.parseUser(result.additionalUserInfo?.username)
                    user?.let {
                        cont.resume(Ok(user))
                    } ?: run {
                        cont.resume(Err(RuntimeException()))
                    }
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // authResult.getCredential().getAccessToken().
                }
                .addOnFailureListener {
                    // Handle failure.
                    cont.resume(Err(it))
                }
        }

    override suspend fun getUser(): UserWithoutInfo? {
        return firebaseAuth.currentUser?.parseUserWithoutInfo()
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}