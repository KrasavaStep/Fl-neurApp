package com.flaneur.core.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun MapViewContainer(
    mapState: MapState,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Map View Container (Lat: ${mapState.center.lat}, Lon: ${mapState.center.lon}, Zoom: ${mapState.zoom})",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
