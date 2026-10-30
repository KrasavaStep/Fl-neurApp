package com.flaneur.app.di

import com.flaneur.core.database.di.databaseModule
import com.flaneur.core.location.di.locationModule
import com.flaneur.core.network.di.networkModule
import com.flaneur.feature.generator.di.generatorModule
import com.flaneur.feature.history.di.historyModule
import com.flaneur.feature.tracking.di.trackingModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        networkModule,
        databaseModule,
        locationModule,
        generatorModule,
        trackingModule,
        historyModule
    )
}
