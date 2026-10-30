package com.flaneur.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fog_cells")
data class FogCellEntity(
    @PrimaryKey val cellId: String,
    val visitedAtMillis: Long,
    val visitCount: Int = 1
)
