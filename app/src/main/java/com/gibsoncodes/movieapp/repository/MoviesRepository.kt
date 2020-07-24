package com.gibsoncodes.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.gibsoncodes.movieapp.model.MovieDetails
import com.gibsoncodes.movieapp.model.Movies
import com.gibsoncodes.movieapp.network.RemoteDataSource
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun fetchPopularMoviesList(
        page: Int, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<List<Movies>>()
        var movieList: List<Movies> = ArrayList()
        remoteDataSource.fetchPopularMoviesList(page = page) {
            it.onSuccess {
                data.whatIfNotNull { response ->
                    movieList = response.results
                    liveData.postValue(movieList)
                    onSuccess()
                }
            }.onError {
                onError(message())
            }.onException { onError(message()) }
        }
        return@withContext liveData

    }

    suspend fun fetchTrendingMovieAllDay(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        var trendingMovieList: List<Movies>
        val liveData: MutableLiveData<List<Movies>> = MutableLiveData()
        remoteDataSource.fetchTrendingMoviesAllDay(page, result = {
            it.onSuccess {
                data.whatIfNotNull { trendingMovies ->
                    trendingMovieList = trendingMovies.results
                    liveData.postValue(trendingMovieList)
                    onSuccess()
                }
            }
            it.onError {
                onError(message())
            }
            it.onException {
                onError(message())
            }
        })
        return@withContext liveData
    }
    suspend fun fetchMovieDetails(movieId:Int,onSuccess: () -> Unit,onError: (String) -> Unit)= withContext(Dispatchers.IO){
        val liveData:MutableLiveData<MovieDetails> = MutableLiveData()
        remoteDataSource.fetchMovieDetailsInfo(movieId = movieId, result = {
            it.onSuccess {
                data.whatIfNotNull { movieDetails ->
                    liveData.postValue(movieDetails)
                    onSuccess()
                }
            }
            it.onError {
                onError(message())
            }
            it.onException {
                onError(message())
            }
        })
        return@withContext liveData
    }

    suspend fun fetchTopRatedMoviesList(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        var topRatedMoviesList: List<Movies>
        val liveData: MutableLiveData<List<Movies>> = MutableLiveData()
        remoteDataSource.fetchTopRatedMoviesList(page = page, result = {
            it.onSuccess {
                data.whatIfNotNull { responseBody ->
                    topRatedMoviesList = responseBody.results
                    liveData.postValue(topRatedMoviesList)
                    onSuccess()
                }

            }
            it.onError {
                onError(message())
            }
            it.onException {
                onError(message())
            }
        })
        return@withContext liveData

    }

    suspend fun fetchLatestMovie(onSuccess: () -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            var latestMovie: Movies?
            val liveData: MutableLiveData<Movies> = MutableLiveData()
            remoteDataSource.fetchLatestMovie {
                it.onSuccess {
                    data.whatIfNotNull { latest ->
                        latestMovie = latest
                        liveData.postValue(latestMovie)
                        onSuccess()
                    }
                }
            }
            return@withContext liveData

        }

    suspend fun fetchNowPlayingMovies(page: Int, onSuccess: () -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            var nowPlayingList: List<Movies>
            val liveData: MutableLiveData<List<Movies>> = MutableLiveData()

            remoteDataSource.fetchNowPlayingMovies(page = page, result = {
                it.onSuccess {
                    data.whatIfNotNull { nowPlaying ->
                        nowPlayingList = nowPlaying.results
                        liveData.postValue(nowPlayingList)
                        onSuccess()
                    }
                }
                it.onError {
                    onError(message())
                }
            it.onException {
                onError(message())
            }
        })
        return@withContext liveData
    }

}