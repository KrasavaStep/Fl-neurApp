package com.flaneur.feature.tracking

import com.flaneur.core.model.GeoPoint

data class ActiveWalkUiState(
    val elapsedTimeSeconds: Long = 0L,
    val distanceCoveredMeters: Double = 0.0,
    val remainingDistanceMeters: Double = 0.0,
    val currentPosition: GeoPoint? = null,
    val trackedPath: List<GeoPoint> = emptyList(),
    val isPaused: Boolean = false,
    val showFinishDialog: Boolean = false
)

sealed interface ActiveWalkUiIntent {
    data object TogglePause : ActiveWalkUiIntent
    data object RequestFinish : ActiveWalkUiIntent
    data object ConfirmFinish : ActiveWalkUiIntent
    data object CancelFinish : ActiveWalkUiIntent
}
