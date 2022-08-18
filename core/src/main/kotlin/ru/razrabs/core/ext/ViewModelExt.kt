package ru.razrabs.core.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val vmCoroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
    throwable.printStackTrace()
}

fun ViewModel.launchIO(
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context = Dispatchers.IO + vmCoroutineExceptionHandler, block = block)