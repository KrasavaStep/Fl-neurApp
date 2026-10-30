package com.flaneur.feature.generator

import com.flaneur.core.location.LocationTracker
import com.flaneur.core.model.GeoPoint
import com.flaneur.core.model.Vibe
import com.flaneur.core.network.GraphHopperApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RouteGeneratorViewModel(
    private val graphHopperApi: GraphHopperApi,
    private val locationTracker: LocationTracker,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow<RouteGeneratorUiState>(RouteGeneratorUiState.Idle)
    val uiState: StateFlow<RouteGeneratorUiState> = _uiState.asStateFlow()

    private val _selectedVibe = MutableStateFlow<Vibe>(Vibe.Quiet)
    val selectedVibe: StateFlow<Vibe> = _selectedVibe.asStateFlow()

    private val _durationMinutes = MutableStateFlow(45)
    val durationMinutes: StateFlow<Int> = _durationMinutes.asStateFlow()

    fun handleIntent(intent: RouteGeneratorUiIntent) {
        when (intent) {
            is RouteGeneratorUiIntent.SelectVibe -> _selectedVibe.value = intent.vibe
            is RouteGeneratorUiIntent.ChangeDuration -> _durationMinutes.value = intent.minutes
            is RouteGeneratorUiIntent.GenerateRoute -> generateRoute()
            is RouteGeneratorUiIntent.StartWalk -> { /* handled by navigation */ }
        }
    }

    private fun generateRoute() {
        scope.launch {
            _uiState.value = RouteGeneratorUiState.Loading
            val currentLoc = locationTracker.currentLocation.value
                ?: GeoPoint(55.7558, 37.6173) // Moscow fallback

            val distanceMeters = (_durationMinutes.value * 80) // ~80m/min speed
            val result = graphHopperApi.fetchRoundTripRoute(
                startPoint = currentLoc,
                distanceMeters = distanceMeters,
                vibe = _selectedVibe.value
            )

            result.onSuccess { route ->
                _uiState.value = RouteGeneratorUiState.RouteReady(
                    route = route,
                    startPoint = currentLoc
                )
            }.onFailure { error ->
                _uiState.value = RouteGeneratorUiState.Error(
                    message = error.message ?: "Не удалось сгенерировать маршрут"
                )
            }
        }
    }
}
