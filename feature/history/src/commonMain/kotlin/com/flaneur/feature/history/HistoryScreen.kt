package com.flaneur.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    modifier: Modifier = Modifier
) {
    val walks by viewModel.walks.collectAsState()

    if (walks.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "История прогулок пуста",
                style = MaterialTheme.typography.titleLarge
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(walks) { walk ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Прогулка #${walk.id}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Дистанция: ${walk.distanceMeters} м",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Длительность: ${walk.durationMinutes} мин",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
