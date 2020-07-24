package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class ShowsResponse(@field:Json(name="results") val showsList:List<Shows>)