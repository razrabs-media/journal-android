package ru.razrabs.core

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openWebPage(context: Context, url: String) {
    context.startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
    )
}