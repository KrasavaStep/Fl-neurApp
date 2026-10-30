package com.flaneur.core.maps

import com.flaneur.core.model.GeoPoint

data class MapState(
    val center: GeoPoint = GeoPoint(55.7558, 37.6173),
    val zoom: Double = 14.0,
    val polylinePoints: List<GeoPoint> = emptyList(),
    val markers: List<GeoPoint> = emptyList()
)
