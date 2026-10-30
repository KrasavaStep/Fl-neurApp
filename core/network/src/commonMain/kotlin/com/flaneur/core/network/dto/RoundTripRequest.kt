package com.flaneur.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoundTripRequest(
    @SerialName("point") val point: List<Double>,
    @SerialName("distance") val distanceMeters: Int = 3000,
    @SerialName("seed") val seed: Long = 0L,
    @SerialName("profile") val profile: String = "foot"
)
