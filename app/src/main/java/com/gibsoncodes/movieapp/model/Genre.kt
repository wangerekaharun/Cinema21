package com.gibsoncodes.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*



@JsonClass(generateAdapter = true)
class Genre constructor(@field:Json(name = "id") val id:Int,
                                @field:Json(name = "name")val genreName:String?) {
    override fun toString(): String {
        return "Genre(id=$id,genreName=$genreName)"
    }

    override fun equals(other: Any?): Boolean {
        return other is Genre && other.id==id &&
                other.genreName==genreName
    }

    override fun hashCode(): Int {
       return Objects.hash(id,genreName)
    }
   /* class Builder{
        @set:JvmSynthetic
        var id:Int =0
        @set:JvmSynthetic
        var genreName:String?=null
        fun setGenreId(id:Int)=apply { this.id=id }
        fun setGenreName(name:String?)=apply { this.genreName=genreName }
    fun build()= Genre(id,genreName!!)
    }*/
}