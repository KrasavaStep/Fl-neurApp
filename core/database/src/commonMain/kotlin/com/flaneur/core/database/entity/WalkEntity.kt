package com.flaneur.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "walks")
data class WalkEntity(
    @PrimaryKey val id: String,
    val distanceMeters: Double,
    val durationMinutes: Int,
    val polylineJson: String,
    val timestampMillis: Long,
    val vibeId: String
)
