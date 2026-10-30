package com.flaneur.feature.generator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaneur.core.model.Vibe

@Composable
fun VibeSelector(
    selectedVibe: Vibe,
    onVibeSelected: (Vibe) -> Unit,
    modifier: Modifier = Modifier
) {
    val vibes = listOf(Vibe.Quiet, Vibe.Parks, Vibe.CoffeeRun, Vibe.UrbanExplore)

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(vibes) { vibe ->
            FilterChip(
                selected = (vibe.id == selectedVibe.id),
                onClick = { onVibeSelected(vibe) },
                label = { Text(vibe.displayName) }
            )
        }
    }
}
