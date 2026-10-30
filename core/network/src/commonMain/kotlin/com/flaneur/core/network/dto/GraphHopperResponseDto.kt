package com.flaneur.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GraphHopperResponseDto(
    @SerialName("paths") val paths: List<PathDto> = emptyList()
)

@Serializable
data class PathDto(
    @SerialName("distance") val distance: Double,
    @SerialName("time") val timeMillis: Long,
    @SerialName("points") val pointsPolyline: String,
    @SerialName("ascend") val ascend: Double = 0.0,
    @SerialName("descend") val descend: Double = 0.0
)
