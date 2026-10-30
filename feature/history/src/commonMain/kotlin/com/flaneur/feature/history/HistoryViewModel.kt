package com.flaneur.feature.history

import com.flaneur.core.database.dao.WalkDao
import com.flaneur.core.database.entity.WalkEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    walkDao: WalkDao,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    val walks: StateFlow<List<WalkEntity>> = walkDao.getAllWalks()
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
