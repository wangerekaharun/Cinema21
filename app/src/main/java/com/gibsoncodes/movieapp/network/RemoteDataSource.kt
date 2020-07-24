package com.gibsoncodes.movieapp.network

import com.gibsoncodes.movieapp.model.*
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.toResponseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieService: MovieService) {

    suspend fun fetchPopularMoviesList(
        page: Int,
        result: (response: ApiResponse<MovieResponse>) -> Unit
    ) {
        movieService.fetchPopularMoviesList(page = page).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchPopularShowsList(
        page: Int,
        result: (response: ApiResponse<ShowsResponse>) -> Unit
    ) {
        movieService.fetchPopularShowsList(page = page).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchShowDetailsInfo(
        showId: Int,
        result: (response: ApiResponse<ShowsDetails>) -> Unit
    ) {
        movieService.fetchShowInfo(showId = showId).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchTopRatedMoviesList(
        page: Int,
        result: (response: ApiResponse<MovieResponse>) -> Unit
    ) {
        movieService.fetchTopRatedMoviesList(page = page).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchUpComingMoviesList(
        page: Int,
        result: (response: ApiResponse<MovieResponse>) -> Unit
    ) {
        movieService.fetchUpComingMoviesList(page = page).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchMovieDetailsInfo(
        movieId: Int,
        result: (response: ApiResponse<MovieDetails>) -> Unit
    ) {
        movieService.fetchMovieInfo(movieId = movieId).toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchLatestMovie(result: (response: ApiResponse<Movies>) -> Unit) {
        movieService.fetchLatestMovie().toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchLatestShow(result: (response: ApiResponse<Shows>) -> Unit) {
        movieService.fetchLatestShow().toResponseDataSource()
            .retry(3, 7000L)
            .request(result)
    }

    suspend fun fetchTrendingMoviesAllDay(
        page: Int,
        result: (response: ApiResponse<MovieResponse>) -> Unit
    ) {
        movieService.fetchTrendingMoviesAllDay(page=page).toResponseDataSource()
            .retry(3,7000L)
            .request(result)
    }
    suspend fun fetchNowPlayingMovies(page: Int,result:(response:ApiResponse<MovieResponse>)->Unit){
        movieService.fetchNowPlayingMoviesList(page=page).toResponseDataSource()
            .retry(3,7000L)
            .request(result)
    }
    suspend fun fetchTrendingShowsAllDay(page:Int,result:(response:ApiResponse<ShowsResponse>)->Unit){
        movieService.fetchTrendingShowsAllDay(page=page).toResponseDataSource()
            .retry(3,7000L)
            .request(result)
    }
}