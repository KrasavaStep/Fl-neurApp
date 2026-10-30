package com.flaneur.app

import androidx.compose.runtime.Composable
import com.flaneur.core.designsystem.theme.FlaneurTheme
import com.flaneur.app.navigation.AppNavGraph

@Composable
fun App() {
    FlaneurTheme {
        AppNavGraph()
    }
}
