package com.flaneur.core.location

import com.flaneur.core.model.GeoPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IosLocationTracker : LocationTracker {
    private val _currentLocation = MutableStateFlow<GeoPoint?>(null)
    override val currentLocation: StateFlow<GeoPoint?> = _currentLocation.asStateFlow()

    private val _isTracking = MutableStateFlow(false)
    override val isTracking: StateFlow<Boolean> = _isTracking.asStateFlow()

    override fun startTracking() {
        _isTracking.value = true
    }

    override fun stopTracking() {
        _isTracking.value = false
    }
}
