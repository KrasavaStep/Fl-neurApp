package com.flaneur.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.flaneur.core.database.dao.FogCellDao
import com.flaneur.core.database.dao.WalkDao
import com.flaneur.core.database.entity.FogCellEntity
import com.flaneur.core.database.entity.WalkEntity

@Database(
    entities = [WalkEntity::class, FogCellEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walkDao(): WalkDao
    abstract fun fogCellDao(): FogCellDao
}

// Suppress constructor warning for Room KMP code generation
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
