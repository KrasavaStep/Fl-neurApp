package com.flaneur.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.flaneur.feature.generator.RouteGeneratorScreen
import com.flaneur.feature.generator.RouteGeneratorViewModel
import com.flaneur.feature.history.HistoryScreen
import com.flaneur.feature.history.HistoryViewModel
import com.flaneur.feature.tracking.ActiveWalkScreen
import com.flaneur.feature.tracking.ActiveWalkViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Generator) }

    val generatorViewModel: RouteGeneratorViewModel = koinInject()
    val activeWalkViewModel: ActiveWalkViewModel = koinInject()
    val historyViewModel: HistoryViewModel = koinInject()

    when (currentScreen) {
        is Screen.Generator -> {
            RouteGeneratorScreen(
                viewModel = generatorViewModel,
                onStartWalk = { currentScreen = Screen.Tracking },
                modifier = modifier
            )
        }
        is Screen.Tracking -> {
            ActiveWalkScreen(
                viewModel = activeWalkViewModel,
                onWalkFinished = { currentScreen = Screen.History },
                modifier = modifier
            )
        }
        is Screen.History -> {
            HistoryScreen(
                viewModel = historyViewModel,
                modifier = modifier
            )
        }
    }
}
