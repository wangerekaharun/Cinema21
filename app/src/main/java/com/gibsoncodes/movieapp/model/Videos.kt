package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Videos (@field:Json(name="id")val id:String, @field:Json(name="key") val key:String,
                   @field:Json(name="name")val name:String,
                   @field:Json(name="type")val type:String,
                   @field:Json(name="size")val size:Double,
                   @field:Json(name="site")val site:String)