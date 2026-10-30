package com.flaneur.feature.generator

import com.flaneur.core.model.GeoPoint
import com.flaneur.core.model.Vibe
import com.flaneur.core.model.WalkRoute

sealed interface RouteGeneratorUiState {
    data object Idle : RouteGeneratorUiState
    data object Loading : RouteGeneratorUiState
    data class RouteReady(
        val route: WalkRoute,
        val startPoint: GeoPoint
    ) : RouteGeneratorUiState
    data class Error(val message: String) : RouteGeneratorUiState
}

sealed interface RouteGeneratorUiIntent {
    data class SelectVibe(val vibe: Vibe) : RouteGeneratorUiIntent
    data class ChangeDuration(val minutes: Int) : RouteGeneratorUiIntent
    data object GenerateRoute : RouteGeneratorUiIntent
    data object StartWalk : RouteGeneratorUiIntent
}
