package com.flaneur.feature.tracking.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun FinishWalkDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Завершить прогулку?") },
        text = { Text("Ваш маршрут будет сохранен в истории.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Завершить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}
