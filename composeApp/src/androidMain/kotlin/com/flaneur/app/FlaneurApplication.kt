package com.flaneur.app

import android.app.Application
import com.flaneur.app.di.initKoin
import com.flaneur.core.database.initDatabaseContext
import com.flaneur.core.location.AndroidLocationTracker
import com.flaneur.core.location.LocationTracker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class FlaneurApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initDatabaseContext(this)

        initKoin {
            androidContext(this@FlaneurApplication)
            modules(module {
                single<LocationTracker> { AndroidLocationTracker(get()) }
            })
        }
    }
}
