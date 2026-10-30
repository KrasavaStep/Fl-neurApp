package com.flaneur.core.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoPoint(
    val lat: Double,
    val lon: Double,
    val altitude: Double? = null
)
