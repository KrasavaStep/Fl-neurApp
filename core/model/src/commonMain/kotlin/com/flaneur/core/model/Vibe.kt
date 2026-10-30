package com.flaneur.core.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Vibe(
    val id: String,
    val displayName: String,
    val iconName: String
) {
    @Serializable
    data object Quiet : Vibe("quiet", "Тишина и покой", "ic_quiet")

    @Serializable
    data object Parks : Vibe("parks", "Парки и скверы", "ic_park")

    @Serializable
    data object CoffeeRun : Vibe("coffee", "Кофейный маршрут", "ic_coffee")

    @Serializable
    data object UrbanExplore : Vibe("urban", "Городские детали", "ic_urban")
}
