package ru.razrabs.design.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.theming.contrastSecondary
import ru.razrabs.design.theming.primary
import ru.razrabs.design.theming.styreneRegular

@Composable
fun LoginViaGithubButton(onClick: () -> Unit) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        backgroundColor = contrastSecondary(),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            Text(
                text = stringResource(id = R.string.login_via_github),
                style = styreneRegular(color = primary(), size = 16),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}