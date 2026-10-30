package com.flaneur.feature.tracking

import com.flaneur.core.database.dao.WalkDao
import com.flaneur.core.database.entity.WalkEntity
import com.flaneur.core.location.LocationTracker
import com.flaneur.core.model.GeoPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActiveWalkViewModel(
    private val locationTracker: LocationTracker,
    private val walkDao: WalkDao,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow(ActiveWalkUiState())
    val uiState: StateFlow<ActiveWalkUiState> = _uiState.asStateFlow()

    init {
        scope.launch {
            locationTracker.currentLocation.collect { loc ->
                loc?.let { newPoint ->
                    val currentPoints = _uiState.value.trackedPath.toMutableList()
                    currentPoints.add(newPoint)
                    _uiState.value = _uiState.value.copy(
                        currentPosition = newPoint,
                        trackedPath = currentPoints
                    )
                }
            }
        }
    }

    fun handleIntent(intent: ActiveWalkUiIntent, onFinished: () -> Unit = {}) {
        when (intent) {
            is ActiveWalkUiIntent.TogglePause -> {
                _uiState.value = _uiState.value.copy(isPaused = !_uiState.value.isPaused)
            }
            is ActiveWalkUiIntent.RequestFinish -> {
                _uiState.value = _uiState.value.copy(showFinishDialog = true)
            }
            is ActiveWalkUiIntent.CancelFinish -> {
                _uiState.value = _uiState.value.copy(showFinishDialog = false)
            }
            is ActiveWalkUiIntent.ConfirmFinish -> {
                saveWalkAndFinish(onFinished)
            }
        }
    }

    private fun saveWalkAndFinish(onFinished: () -> Unit) {
        scope.launch {
            val state = _uiState.value
            val entity = WalkEntity(
                id = "walk_${state.elapsedTimeSeconds}",
                distanceMeters = state.distanceCoveredMeters,
                durationMinutes = (state.elapsedTimeSeconds / 60).toInt(),
                polylineJson = "[]",
                timestampMillis = 1000L,
                vibeId = "quiet"
            )
            walkDao.insertWalk(entity)
            locationTracker.stopTracking()
            onFinished()
        }
    }
}
