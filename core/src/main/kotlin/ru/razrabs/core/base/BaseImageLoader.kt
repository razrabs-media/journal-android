package ru.razrabs.core.base

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache

open class BaseImageLoader(diskCachePath: String, private val context: Context) {
    val loader = ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.35)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve(diskCachePath))
                .maxSizePercent(0.1)
                .build()
        }
        .build()
}