package com.flaneur.app

import androidx.compose.ui.window.ComposeUIViewController
import com.flaneur.core.location.IosLocationTracker
import com.flaneur.core.location.LocationTracker
import com.flaneur.app.di.initKoin
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController {
    App()
}

fun initIosKoin() {
    initKoin {
        modules(module {
            single<LocationTracker> { IosLocationTracker() }
        })
    }
}
