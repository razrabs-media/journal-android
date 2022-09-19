package ru.razrabs.design.components.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.razrabs.core.R
import ru.razrabs.core.constants.*
import ru.razrabs.core.openEmail
import ru.razrabs.core.openWebPage
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.clickableWithoutRipple
import ru.razrabs.design.theming.*

@Composable
fun Footer() {
    val uriHandler = LocalUriHandler.current
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        VerticalSpacer(height = 16)
        Divider(modifier = Modifier.fillMaxWidth(), color = logo())
        VerticalSpacer(height = 16)
        Text(
            text = stringResource(id = R.string.smartass),
            style = styreneBold(color = secondary60(), size = 12, letterSpacing = 1)
        )
        VerticalSpacer(height = 12)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FooterText(stringResource(id = R.string.about_us)) {
                    uriHandler.openUri(ABOUT_US_LINK)
                }
                FooterText(stringResource(id = R.string.contact)) {
                    try {
                        openEmail(ctx, CONTACT_EMAIL_ADDRESS)
                    } catch (e: Exception) {
                        Toast.makeText(ctx, ctx.getString(R.string.no_email_app), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FooterText(stringResource(id = R.string.github)) {
                    uriHandler.openUri(GITHUB_LINK)
                }
                FooterText(stringResource(id = R.string.yt)) {
                    uriHandler.openUri(YT_LINK)
                }
                FooterText(stringResource(id = R.string.tg)) {
                    uriHandler.openUri(TG_LINK)
                }
                FooterText(stringResource(id = R.string.tw)) {
                    uriHandler.openUri(TW_LINK)
                }
            }
        }
    }
}

@Composable
fun FooterText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = styreneRegular(color = logo(), size = 12),
        modifier = Modifier.clickableWithoutRipple(onClick)
    )
}

@Preview
@Composable
fun PreviewFooter() {
    Footer()
}