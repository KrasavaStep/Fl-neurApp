package com.flaneur.app.navigation

sealed class Screen(val route: String) {
    data object Generator : Screen("generator")
    data object Tracking : Screen("tracking")
    data object History : Screen("history")
}
