package ru.razrabs.design.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.theming.primary
import ru.razrabs.design.theming.styreneMedium
import ru.razrabs.design.theming.styreneRegular

@Composable
fun ProfileSignedOutInfoBlock() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {
        Text(
            text = stringResource(id = R.string.we_login_via_github),
            style = styreneMedium(color = primary(), size = 24)
        )
        Text(
            text = stringResource(id = R.string.after_login),
            style = styreneRegular(color = primary(), size = 16)
        )
    }
}

@Preview
@Composable
fun PreviewProfileInfoBlock() {
    ProfileSignedOutInfoBlock()
}