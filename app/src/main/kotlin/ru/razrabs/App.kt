package ru.razrabs

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*
import ru.razrabs.network.NetworkModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                NetworkModule,
                FeedModule,
                CommentModule,
                ProfileModule,
                AuthModule,
                HomeModule
            )
        }
    }
}