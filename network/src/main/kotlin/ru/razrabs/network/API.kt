package ru.razrabs.network

import ru.razrabs.core.ext.Result

interface API {
    suspend fun getCurrentFrontPage(): Result<Unit, Exception>
}