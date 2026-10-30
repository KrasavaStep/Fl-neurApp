package com.flaneur.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flaneur.core.database.entity.WalkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWalk(walk: WalkEntity)

    @Query("SELECT * FROM walks ORDER BY timestampMillis DESC")
    fun getAllWalks(): Flow<List<WalkEntity>>

    @Query("SELECT * FROM walks WHERE id = :id")
    suspend fun getWalkById(id: String): WalkEntity?

    @Query("DELETE FROM walks WHERE id = :id")
    suspend fun deleteWalkById(id: String)
}
