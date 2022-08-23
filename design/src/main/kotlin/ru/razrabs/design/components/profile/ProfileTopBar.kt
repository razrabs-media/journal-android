package ru.razrabs.design.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.subcomponents.common.VectorIcon
import ru.razrabs.design.theming.primary
import ru.razrabs.design.theming.styreneRegular
import ru.razrabs.design.unboundClickable

@Composable
fun ProfileTopBar(onBackAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.acc),
            style = styreneRegular(color = primary(), size = 14)
        )
        VectorIcon(
            icon = ru.razrabs.design.R.drawable.ic_close,
            modifier = Modifier.unboundClickable(onBackAction)
        )
    }
}

@Preview
@Composable
fun PreviewProfileTopBar() {
    ProfileTopBar {

    }
}