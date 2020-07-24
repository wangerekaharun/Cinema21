package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowsGenre(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)