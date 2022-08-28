package ru.razrabs.feature_auth.github

import android.app.Activity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import org.koin.core.annotation.Single


@Single
class GitHubAuthHandler() {
    fun auth(activity: Activity) {
        val provider = OAuthProvider.newBuilder("github.com")
        val firebaseAuth = FirebaseAuth.getInstance()
        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener(
                    object : OnSuccessListener<AuthResult?> {
                        override fun onSuccess(authResult: AuthResult?) {
                            // User is signed in.
                            // IdP data available in
                            // authResult.getAdditionalUserInfo().getProfile().
                            // The OAuth access token can also be retrieved:
                            // authResult.getCredential().getAccessToken().
                        }
                    })
                .addOnFailureListener(
                    object : OnFailureListener {
                        override fun onFailure(p0: java.lang.Exception) {
                            TODO("Not yet implemented")
                        }
                    })
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.
        }
        firebaseAuth
            .startActivityForSignInWithProvider(activity, provider.build())
            .addOnSuccessListener {
                println(it)
                println(it)
                println(it)
                // User is signed in.
                // IdP data available in
                // authResult.getAdditionalUserInfo().getProfile().
                // The OAuth access token can also be retrieved:
                // authResult.getCredential().getAccessToken().
            }
            .addOnFailureListener {
                // Handle failure.
                println(it)
                println(it)
                println(it)
            }
    }
}