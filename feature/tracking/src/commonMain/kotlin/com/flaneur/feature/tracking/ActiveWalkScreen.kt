package com.flaneur.feature.tracking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaneur.core.designsystem.components.FlaneurButton
import com.flaneur.core.maps.MapState
import com.flaneur.core.maps.MapViewContainer
import com.flaneur.feature.tracking.components.FinishWalkDialog
import com.flaneur.feature.tracking.components.WalkHudOverlay

@Composable
fun ActiveWalkScreen(
    viewModel: ActiveWalkViewModel,
    onWalkFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        val mapState = MapState(
            center = uiState.currentPosition ?: com.flaneur.core.model.GeoPoint(55.7558, 37.6173),
            polylinePoints = uiState.trackedPath
        )

        MapViewContainer(
            mapState = mapState,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            WalkHudOverlay(
                elapsedTimeSeconds = uiState.elapsedTimeSeconds,
                distanceCoveredMeters = uiState.distanceCoveredMeters
            )

            Spacer(modifier = Modifier.height(12.dp))

            FlaneurButton(
                text = "Завершить прогулку",
                onClick = { viewModel.handleIntent(ActiveWalkUiIntent.RequestFinish) }
            )
        }

        if (uiState.showFinishDialog) {
            FinishWalkDialog(
                onConfirm = {
                    viewModel.handleIntent(ActiveWalkUiIntent.ConfirmFinish, onWalkFinished)
                },
                onDismiss = {
                    viewModel.handleIntent(ActiveWalkUiIntent.CancelFinish)
                }
            )
        }
    }
}
