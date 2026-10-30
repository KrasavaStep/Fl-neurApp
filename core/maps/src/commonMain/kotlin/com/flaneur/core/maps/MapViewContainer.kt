package com.flaneur.core.maps

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MapViewContainer(
    mapState: MapState,
    modifier: Modifier = Modifier
)
