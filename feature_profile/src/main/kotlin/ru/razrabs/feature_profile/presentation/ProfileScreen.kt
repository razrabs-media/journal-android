package ru.razrabs.feature_profile.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import org.koin.androidx.compose.getViewModel
import ru.razrabs.design.components.common.LoginViaGithubButton
import ru.razrabs.design.components.profile.ProfileInfoBlock
import ru.razrabs.design.components.profile.ProfileTopBar
import ru.razrabs.core.R

@Composable
fun ProfileScreen(onBackAction: () -> Unit, vm: ProfileViewModel = getViewModel()) {
    val state = vm.state.uiState.collectAsState().value
    ProfileContent(onBackAction = onBackAction, state = state)
}

@Composable
fun ProfileContent(onBackAction: () -> Unit, state: ProfileViewModel.State) {
    Scaffold(topBar = {
        ProfileTopBar(onBackAction = onBackAction)
    },
        bottomBar = {
            LoginViaGithubButton(
                text = if (state.loggedIn) stringResource(id = R.string.log_out) else stringResource(
                    id = R.string.login_via_github
                )
            ) {}
        }) {
        if (!state.loggedIn) {
            ProfileInfoBlock()
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
    ProfileContent(onBackAction = {}, state = state)
}