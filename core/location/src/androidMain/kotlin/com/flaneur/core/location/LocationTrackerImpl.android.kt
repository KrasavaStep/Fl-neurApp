package com.flaneur.core.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.flaneur.core.model.GeoPoint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidLocationTracker(
    private val context: Context
) : LocationTracker {

    private val fusedClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _currentLocation = MutableStateFlow<GeoPoint?>(null)
    override val currentLocation: StateFlow<GeoPoint?> = _currentLocation.asStateFlow()

    private val _isTracking = MutableStateFlow(false)
    override val isTracking: StateFlow<Boolean> = _isTracking.asStateFlow()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { loc ->
                _currentLocation.value = GeoPoint(
                    lat = loc.latitude,
                    lon = loc.longitude,
                    altitude = loc.altitude
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun startTracking() {
        if (_isTracking.value) return
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L)
            .setMinUpdateIntervalMillis(2000L)
            .build()

        fusedClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        _isTracking.value = true
    }

    override fun stopTracking() {
        if (!_isTracking.value) return
        fusedClient.removeLocationUpdates(locationCallback)
        _isTracking.value = false
    }
}
