package com.gibsoncodes.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.gibsoncodes.movieapp.model.Shows
import com.gibsoncodes.movieapp.model.ShowsDetails
import com.gibsoncodes.movieapp.network.RemoteDataSource
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun fetchPopularShowsList(page: Int, onSuccess: () -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            var popularShows: List<Shows> = ArrayList()
            val liveData: MutableLiveData<List<Shows>> = MutableLiveData()
            remoteDataSource.fetchPopularShowsList(page = page, result = {
                it.onSuccess {
                    data.whatIfNotNull { response ->
                        popularShows = response.showsList
                        liveData.postValue(popularShows)
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

    suspend fun fetchShowInfo(showId: Int, onSuccess: () -> Unit, onError: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData: MutableLiveData<ShowsDetails> = MutableLiveData()
            remoteDataSource.fetchShowDetailsInfo(showId = showId, result = {
                it.onSuccess {
                    data.whatIfNotNull { response ->
                        liveData.postValue(response)
                    }
                }
                it.onException {
                    onError(message())
                }
            })
            return@withContext liveData
        }

    suspend fun fetchTrendingShowsAllDay(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        var trendingShows: List<Shows> = ArrayList()
        val liveData: MutableLiveData<List<Shows>> = MutableLiveData()
        remoteDataSource.fetchTrendingShowsAllDay(page = page, result = {
            it.onSuccess {
                data.whatIfNotNull { response ->
                    trendingShows = response.showsList
                    liveData.postValue(trendingShows)
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
        // liveData.apply { postValue(trendingShows) }
    }
    suspend fun fetchLatestShows(onSuccess: () -> Unit,onError: (String) -> Unit)= withContext(Dispatchers.IO){
       val liveData:MutableLiveData<Shows> = MutableLiveData()
        var show:Shows?=null
        remoteDataSource.fetchLatestShow {
            it.onSuccess {
                data.whatIfNotNull { latestShow ->
                    show = latestShow
                    liveData.postValue(latestShow)
                    onSuccess()
                }

            }
            it.onError {
                onError(message())
            }
            it.onException {
                onError(message())
            }
        }
        return@withContext liveData
    }

}