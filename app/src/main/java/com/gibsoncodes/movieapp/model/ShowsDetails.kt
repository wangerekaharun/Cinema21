package com.gibsoncodes.movieapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowsDetails(
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "backdrop_path") val backdropPath: String,
    @field:Json(name = "episode_run_time")
    val episodeRunTime: List<Double>?,
    @field:Json(name = "first_air_date") val firstAirDate: String?,
    @field:Json(name = "genres") val genres: List<ShowsGenre>?,
    @field:Json(name = "in_production") val inProduction: Boolean?,
    @field:Json(name = "languages") val languages: List<String>?,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "number_of_episodes") val numberOfEpisodes: Int,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "networks") val
    networks: List<Networks>?,
    @field:Json(name = "number_of_seasons") val numberOfSeasons: Int?,
    @field:Json(name = "created_by") val createdBy: List<CreatedBy>?
) {
    @JsonClass(generateAdapter = true)
    data class Networks(
        @field:Json(name = "name")
        val name: String,
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "logo_path") val logoPath: String
    )

    @JsonClass(generateAdapter = true)
    data class CreatedBy(@field:Json(name = "name") val name: String)
}