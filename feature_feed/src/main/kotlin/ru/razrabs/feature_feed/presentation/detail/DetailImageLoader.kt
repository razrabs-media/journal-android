package ru.razrabs.feature_feed.presentation.detail

import android.content.Context
import org.koin.core.annotation.Single
import ru.razrabs.core.base.BaseImageLoader

@Single
class DetailImageLoader(context: Context) : BaseImageLoader("detail", context) {
}