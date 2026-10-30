package com.flaneur.core.database.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.flaneur.core.database.AppDatabase
import com.flaneur.core.database.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<AppDatabase>().walkDao() }
    single { get<AppDatabase>().fogCellDao() }
}
