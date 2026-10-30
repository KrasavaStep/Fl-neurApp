package com.flaneur.core.location

import com.flaneur.core.model.GeoPoint
import kotlinx.coroutines.flow.StateFlow

interface LocationTracker {
    val currentLocation: StateFlow<GeoPoint?>
    val isTracking: StateFlow<Boolean>

    fun startTracking()
    fun stopTracking()
}
