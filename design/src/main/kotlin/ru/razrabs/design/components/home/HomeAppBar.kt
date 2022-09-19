package ru.razrabs.design.components.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.clickableWithoutRipple
import ru.razrabs.design.subcomponents.common.VectorIconColorFilter
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.styreneBold
import ru.razrabs.design.theming.styreneRegular
import ru.razrabs.design.unboundClickable

@Composable
fun HomeAppBar(
    backShown: Boolean,
    onBackAction: () -> Unit,
    onProfileClicked: () -> Unit,
    initials: String?
) {
    val backButtonVisibility by animateFloatAsState(targetValue = if (backShown) 1f else 0f)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        VerticalSpacer(height = 16)
        Box(modifier = Modifier.fillMaxWidth()) {
            VectorIconColorFilter(
                icon = ru.razrabs.design.R.drawable.ic_back,
                modifier = Modifier
                    .unboundClickable(onBackAction)
                    .alpha(backButtonVisibility)
                    .align(
                        Alignment.CenterStart
                    ),
                color = logo()
            )
            if (initials != null) {
                UserNameButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    initials = initials,
                    onClick = onProfileClicked
                )
            } else {
                Text(
                    text = stringResource(id = R.string.acc),
                    style = styreneRegular(color = logo(), size = 16),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickableWithoutRipple(onProfileClicked)
                )
            }
            Text(
                text = stringResource(id = R.string.razrabs),
                style = styreneBold(color = logo(), size = 24, letterSpacing = 3),
                modifier = Modifier.align(Alignment.Center)
            )
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
    HomeAppBar(true, onBackAction = {}, onProfileClicked = {}, initials = "LN")
}