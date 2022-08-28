package ru.razrabs.feature_profile.presentation

import android.content.Context
import org.koin.core.annotation.Single
import ru.razrabs.core.base.BaseImageLoader

@Single
class ProfileImageLoader(context: Context): BaseImageLoader("profile_cache", context) {
}