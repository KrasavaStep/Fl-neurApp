package com.flaneur.core.maps

import com.flaneur.core.model.GeoPoint

fun List<GeoPoint>.toGeoJsonLineString(): String {
    val coordinates = joinToString(",") { "[${it.lon}, ${it.lat}]" }
    return """
        {
          "type": "Feature",
          "geometry": {
            "type": "LineString",
            "coordinates": [$coordinates]
          }
        }
    """.trimIndent()
}
