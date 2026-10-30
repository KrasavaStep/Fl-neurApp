package com.flaneur.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Spot(
    val id: String,
    val name: String,
    val description: String,
    val location: GeoPoint,
    val category: String,
    val imageUrl: String? = null
)
