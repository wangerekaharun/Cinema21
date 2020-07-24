package com.gibsoncodes.movieapp.network

import com.gibsoncodes.movieapp.model.*
import com.skydoves.sandwich.DataSource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/latest")
    suspend fun fetchLatestMovie(@Query("language") language:String="en-US",
                                      @Query("page") page:Int=1):DataSource<Movies> // returns a single movie
    @GET("tv/latest")
    suspend fun fetchLatestShow(@Query("language")language: String="en-US",
    @Query("page") page:Int=1):DataSource<Shows>

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMoviesList(@Query("language")language: String="en-US",
    @Query("page")page:Int=1):DataSource<MovieResponse>

    @GET("movie/top-rated")
    suspend fun fetchTopRatedMoviesList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<MovieResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpComingMoviesList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<MovieResponse>

    @GET("movie/popular")
    suspend fun fetchPopularMoviesList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<MovieResponse>

    @GET("tv/popular")
    suspend fun fetchPopularShowsList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<ShowsResponse>

    @GET("movie/{id}")
    suspend fun fetchMovieInfo(
        @Path("id") movieId: Int,
        @Query("append_to_response") append_to_response: String = "videos"
    ): DataSource<MovieDetails>

    @GET("tv/{id}")
    suspend fun fetchShowInfo(
        @Path("id") showId: Int,
        @Query("append_to_response") append_to_response: String = "videos"
    ): DataSource<ShowsDetails>

    @GET("trending/movie/day")
    suspend fun fetchTrendingMoviesAllDay(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<MovieResponse>

    @GET("trending/tv/day")
    suspend fun fetchTrendingShowsAllDay(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): DataSource<ShowsResponse>


}