package ru.razrabs

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.CommentModuleModule
import org.koin.ksp.generated.FeedModuleModule
import org.koin.ksp.generated.NetworkModuleModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                NetworkModuleModule,
                FeedModuleModule,
                CommentModuleModule
            )
        }
    }
}