package com.flaneur.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flaneur.core.database.entity.FogCellEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FogCellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cell: FogCellEntity)

    @Query("SELECT * FROM fog_cells")
    fun getAllVisitedCells(): Flow<List<FogCellEntity>>

    @Query("SELECT COUNT(*) FROM fog_cells")
    suspend fun getVisitedCount(): Int
}
