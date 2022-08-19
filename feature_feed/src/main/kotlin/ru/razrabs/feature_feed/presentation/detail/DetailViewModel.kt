package ru.razrabs.feature_feed.presentation.detail

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import ru.razrabs.feature_feed.domain.LoadPost

@KoinViewModel
class DetailViewModel(private val postUid: String, private val loadPost: LoadPost) : ViewModel() {



}