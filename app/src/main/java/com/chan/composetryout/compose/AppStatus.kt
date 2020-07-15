package com.chan.composetryout.compose

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue

object AppStatus {
    var currentScreen by mutableStateOf<Screen>(Screen.HOME)
}

enum class Screen{
    HOME, LIST, ANDROIDVIEW, OBSERVE_PATTERN
}

fun navigateTo(destination: Screen) {
    AppStatus.currentScreen = destination
}