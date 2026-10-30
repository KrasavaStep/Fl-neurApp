package com.flaneur.core.network

import com.flaneur.core.model.GeoPoint
import com.flaneur.core.model.Vibe
import com.flaneur.core.model.WalkRoute
import com.flaneur.core.network.dto.GraphHopperResponseDto
import com.flaneur.core.network.util.PolylineDecoder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GraphHopperApi(
    private val client: HttpClient,
    private val apiKey: String = ""
) {
    suspend fun fetchRoundTripRoute(
        startPoint: GeoPoint,
        distanceMeters: Int,
        vibe: Vibe
    ): Result<WalkRoute> {
        return runCatching {
            val response: GraphHopperResponseDto = client.get("https://graphhopper.com/api/1/route") {
                parameter("point", "${startPoint.lat},${startPoint.lon}")
                parameter("vehicle", "foot")
                parameter("algorithm", "round_trip")
                parameter("round_trip.distance", distanceMeters)
                parameter("key", apiKey)
                parameter("points_encoded", "true")
            }.body()

            val path = response.paths.firstOrNull()
                ?: throw IllegalStateException("No route returned from GraphHopper")

            val points = PolylineDecoder.decode(path.pointsPolyline)
            WalkRoute(
                id = "gh_${path.timeMillis}",
                points = points,
                distanceMeters = path.distance,
                estimatedDurationMinutes = (path.timeMillis / 60000).toInt(),
                elevationGainMeters = path.ascend,
                vibe = vibe
            )
        }
    }
}
