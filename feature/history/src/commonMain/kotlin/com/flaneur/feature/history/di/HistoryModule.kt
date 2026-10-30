package com.flaneur.feature.history.di

import com.flaneur.feature.history.HistoryViewModel
import org.koin.dsl.module

val historyModule = module {
    single { HistoryViewModel(get()) }
}
