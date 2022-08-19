package ru.razrabs.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class UiStateFlow<T>(
    initState: T,
) {
    private val _uiState = MutableStateFlow(initState)
    val uiState: StateFlow<T> = _uiState.asStateFlow()
    val value: T get() = uiState.value

    fun update(function: (T) -> T) {
        _uiState.update(function)
    }
}