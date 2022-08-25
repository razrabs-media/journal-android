package ru.razrabs.network

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

val networkModule = module {
    single<API> {APIImpl()}
}