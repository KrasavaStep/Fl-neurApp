package com.flaneur.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class WalkStatus {
    IDLE,
    ACTIVE,
    PAUSED,
    COMPLETED
}

@Serializable
data class WalkSession(
    val id: String,
    val targetRoute: WalkRoute,
    val currentPoints: List<GeoPoint> = emptyList(),
    val startTimeMillis: Long = 0L,
    val elapsedTimeSeconds: Long = 0L,
    val status: WalkStatus = WalkStatus.IDLE
)
