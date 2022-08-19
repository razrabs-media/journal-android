package ru.razrabs.feature_feed.presentation.preview

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.feature_feed.domain.LoadByFeed
import ru.razrabs.feature_feed.domain.LoadFrontPage
import ru.razrabs.feature_feed.domain.LoadPost

@KoinViewModel
class FeedViewModel(
    private val loadByFeed: LoadByFeed,
    private val loadFrontPage: LoadFrontPage
) : ViewModel() {


}