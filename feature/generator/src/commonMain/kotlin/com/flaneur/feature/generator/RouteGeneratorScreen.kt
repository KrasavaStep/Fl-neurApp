package com.flaneur.feature.generator

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
import com.flaneur.core.designsystem.components.DurationSlider
import com.flaneur.core.designsystem.components.FlaneurButton
import com.flaneur.core.designsystem.components.LoadingOverlay
import com.flaneur.core.maps.MapState
import com.flaneur.core.maps.MapViewContainer
import com.flaneur.feature.generator.components.RouteSummaryCard
import com.flaneur.feature.generator.components.VibeSelector

@Composable
fun RouteGeneratorScreen(
    viewModel: RouteGeneratorViewModel,
    onStartWalk: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedVibe by viewModel.selectedVibe.collectAsState()
    val durationMinutes by viewModel.durationMinutes.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        val mapState = when (val state = uiState) {
            is RouteGeneratorUiState.RouteReady -> MapState(
                center = state.startPoint,
                polylinePoints = state.route.points
            )
            else -> MapState()
        }

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
            VibeSelector(
                selectedVibe = selectedVibe,
                onVibeSelected = { viewModel.handleIntent(RouteGeneratorUiIntent.SelectVibe(it)) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            DurationSlider(
                durationMinutes = durationMinutes,
                onDurationChange = { viewModel.handleIntent(RouteGeneratorUiIntent.ChangeDuration(it)) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            when (val state = uiState) {
                is RouteGeneratorUiState.RouteReady -> {
                    RouteSummaryCard(
                        route = state.route,
                        onStartClick = onStartWalk
                    )
                }
                else -> {
                    FlaneurButton(
                        text = "Сгенерировать маршрут",
                        onClick = { viewModel.handleIntent(RouteGeneratorUiIntent.GenerateRoute) }
                    )
                }
            }
        }

        if (uiState is RouteGeneratorUiState.Loading) {
            LoadingOverlay()
        }
    }
}
