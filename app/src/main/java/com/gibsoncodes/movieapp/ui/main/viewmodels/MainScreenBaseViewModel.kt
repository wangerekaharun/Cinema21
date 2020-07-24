package com.gibsoncodes.movieapp.ui.main.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gibsoncodes.movieapp.base.BaseViewModel
import com.gibsoncodes.movieapp.model.Movies
import com.gibsoncodes.movieapp.model.Shows
import com.gibsoncodes.movieapp.repository.MoviesRepository
import com.gibsoncodes.movieapp.repository.ShowsRepository
import timber.log.Timber

class MainScreenBaseViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository, private val showsRepository: ShowsRepository): BaseViewModel(){
 val isLoading:ObservableBoolean= ObservableBoolean(true)
    val trendingMoviesAllDay: LiveData<List<Movies>>
    val trendingShowsAllDay: LiveData<List<Shows>>
    val latestMovie : LiveData<Movies>
    val latestShow: LiveData<Shows>
    private val isConnected:MutableLiveData<Boolean> = MutableLiveData()
    private var pageLiveData:MutableLiveData<Int> = MutableLiveData()
    fun setPage(page:Int){
        pageLiveData.value=page
    }


    init {
        isLoading.set(true)
        trendingMoviesAllDay= launchOnViewModelScope {
            this.moviesRepository.fetchTrendingMovieAllDay(page=1,onSuccess = {isLoading.set(false)},onError = {
                isLoading.set(false)
                Timber.e("An error occurred:  $it")
            })
        }
        trendingShowsAllDay= launchOnViewModelScope {
            this.showsRepository.fetchTrendingShowsAllDay(page=1,onSuccess = {isLoading.set(false)},onError = {
                Timber.e("An error occurred:  $it")
                isLoading.set(false)
            })
        }
        latestMovie= launchOnViewModelScope {
            this.moviesRepository.fetchLatestMovie(onSuccess = {
                isLoading.set(false)
                Timber.d("success:")
            },onError = {
                Timber.e("an error occurred :$it")
                isLoading.set(false)

            })
        }

        latestShow= launchOnViewModelScope {
            this.showsRepository.fetchLatestShows(onSuccess = {
                isLoading.set(false)
                Timber.d("Success")
            },onError = {
                isLoading.set(false)
                Timber.e("An error occurred:  $it")
            })
        }
    }
}