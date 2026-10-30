package com.flaneur.feature.tracking.di

import com.flaneur.feature.tracking.ActiveWalkViewModel
import org.koin.dsl.module

val trackingModule = module {
    single { ActiveWalkViewModel(get(), get()) }
}
