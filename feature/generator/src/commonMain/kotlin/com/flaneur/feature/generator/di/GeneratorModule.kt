package com.flaneur.feature.generator.di

import com.flaneur.feature.generator.RouteGeneratorViewModel
import org.koin.dsl.module

val generatorModule = module {
    single { RouteGeneratorViewModel(get(), get()) }
}
