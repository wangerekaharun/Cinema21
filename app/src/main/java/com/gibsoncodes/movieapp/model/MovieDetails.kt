package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdrop_path: String,
    @field:Json(name = "genres") val genres: List<Genre>,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "runtime") val runtime: Double,
    @field:Json(name = "release_date") val releaseData: String,
    @field:Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguages>,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "videos") val videos: VideoResponse
)

@JsonClass(generateAdapter = true)
data class VideoResponse(@field:Json(name = "results") val result: List<Videos>)
