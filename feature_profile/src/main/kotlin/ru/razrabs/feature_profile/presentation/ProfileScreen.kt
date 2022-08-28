package ru.razrabs.feature_profile.presentation

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.razrabs.design.components.common.LoginViaGithubButton
import ru.razrabs.design.components.profile.ProfileSignedOutInfoBlock
import ru.razrabs.design.components.profile.ProfileTopBar
import ru.razrabs.core.R
import ru.razrabs.design.components.profile.ProfileSignedInBlock

@Composable
fun ProfileScreen(onBackAction: () -> Unit, vm: ProfileViewModel = getViewModel()) {
    val state = vm.state.uiState.collectAsState().value
    val ctx = LocalContext.current
    ProfileContent(
        onBackAction = onBackAction,
        state = state,
        onLoginClicked = { vm.auth(ctx as Activity) },
        onSignOutClicked = { vm.signOut() })
}

@Composable
fun ProfileContent(
    onBackAction: () -> Unit,
    state: ProfileViewModel.State,
    profileImageLoader: ProfileImageLoader = get(),
    onLoginClicked: () -> Unit,
    onSignOutClicked: () -> Unit
) {
    Scaffold(topBar = {
        ProfileTopBar(onBackAction = onBackAction)
    },
        bottomBar = {
            LoginViaGithubButton(
                text = if (state.loggedIn) stringResource(id = R.string.log_out) else stringResource(
                    id = R.string.login_via_github
                ),
                onClick = if(state.loggedIn) onSignOutClicked else onLoginClicked
            )
        }) {
        if (!state.loggedIn) {
            ProfileSignedOutInfoBlock()
        } else {
            ProfileSignedInBlock(
                imageLoader = profileImageLoader,
                fullName = state.fullUserName?.uppercase() ?: "",
                username = state.username?.uppercase() ?: "",
                commentCount = 0,
                articleCount = 17,
                signupDate = "asd",
                sinceSignupDate = "gggg",
                avatarUrl = state.avatarUrl ?: ""
            )
        }
    }
}

class StateProvider : CollectionPreviewParameterProvider<ProfileViewModel.State>(
    listOf(
        ProfileViewModel.State(loggedIn = false),
        ProfileViewModel.State(loggedIn = true),
    )
)

@Preview(showSystemUi = true)
@Composable
fun PreviewProfile(@PreviewParameter(StateProvider::class) state: ProfileViewModel.State) {
    val ctx = LocalContext.current
    ProfileContent(
        onBackAction = {},
        state = state,
        profileImageLoader = ProfileImageLoader(ctx),
        onLoginClicked = {},
        onSignOutClicked = {})
}