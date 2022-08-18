package ru.razrabs.design.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.styreneBold

@Composable
fun HomeAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        VerticalSpacer(height = 16)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Text(
                text = stringResource(id = R.string.razrabs),
                style = styreneBold(color = logo(), size = 24, letterSpacing = 3),
            )

            UserNameButton(initials = "LN") {

            }
        }
        VerticalSpacer(height = 16)
        Divider(
            modifier = Modifier
                .fillMaxWidth(), color = logo()
        )
    }
}

@Preview
@Composable
fun PreviewHomeAppBar() {
    HomeAppBar()
}