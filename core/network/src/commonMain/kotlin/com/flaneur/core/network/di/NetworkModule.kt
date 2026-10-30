package com.flaneur.core.network.di

import com.flaneur.core.network.GraphHopperApi
import com.flaneur.core.network.HttpClientFactory
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create() }
    single { GraphHopperApi(get()) }
}
