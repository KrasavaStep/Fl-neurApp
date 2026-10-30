package com.flaneur.core.model

import kotlinx.serialization.Serializable

@Serializable
data class WalkRoute(
    val id: String,
    val points: List<GeoPoint>,
    val distanceMeters: Double,
    val estimatedDurationMinutes: Int,
    val elevationGainMeters: Double = 0.0,
    val vibe: Vibe = Vibe.Quiet,
    val spots: List<Spot> = emptyList()
)
