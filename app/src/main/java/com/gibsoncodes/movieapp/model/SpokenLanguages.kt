package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguages(@field:Json(name = "name") val name: String)