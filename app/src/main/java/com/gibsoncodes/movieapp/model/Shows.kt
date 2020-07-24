package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shows(
    @field:Json(name = "original_name") val original_name: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "popularity") val popularity: Double?,
    @field:Json(name = "vote_average") val vote_average: Double?,
    @field:Json(name = "origin_country") val origin_country: List<String>?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "poster_path") val posterPath: String,
    @field:Json(name = "backdrop_path") val backdropPath: String,
    @field:Json(name = "first_air_date") val firstAirDate: String?,
    @field:Json(name = "original_language") val original_language: String?,
    @field:Json(name = "id") val id: Int
)
