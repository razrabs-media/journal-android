package ru.razrabs.feature_feed.presentation.preview

import android.content.Context
import org.koin.core.annotation.Single
import ru.razrabs.core.base.BaseImageLoader

@Single
class FeedImageLoader(context: Context) : BaseImageLoader("feed_cache", context) {
}