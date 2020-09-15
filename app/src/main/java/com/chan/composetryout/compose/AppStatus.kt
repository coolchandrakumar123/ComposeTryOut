package com.chan.composetryout.compose

import androidx.compose.runtime.mutableStateOf

object AppStatus {
    var currentScreen = Screen.HOME
}

enum class Screen{
    HOME, LIST, ANDROIDVIEW, OBSERVE_PATTERN
}

fun navigateTo(destination: Screen) {
    AppStatus.currentScreen = destination
}