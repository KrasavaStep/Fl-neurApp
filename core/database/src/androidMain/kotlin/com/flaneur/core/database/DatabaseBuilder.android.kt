package com.flaneur.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

private lateinit var applicationContext: Context

fun initDatabaseContext(context: Context) {
    applicationContext = context.applicationContext
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = applicationContext.getDatabasePath("flaneur.db")
    return Room.databaseBuilder<AppDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}
