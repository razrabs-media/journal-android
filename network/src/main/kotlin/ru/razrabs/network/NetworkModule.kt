package ru.razrabs.network

import org.koin.dsl.module

val NetworkModule = module {
    single<API> {APIImpl()}
}