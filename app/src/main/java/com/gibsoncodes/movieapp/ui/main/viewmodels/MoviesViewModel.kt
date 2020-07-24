package com.gibsoncodes.movieapp.ui.main.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.gibsoncodes.movieapp.base.BaseViewModel
import com.gibsoncodes.movieapp.model.Movies
import com.gibsoncodes.movieapp.repository.MoviesRepository
import timber.log.Timber

class MoviesViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository):BaseViewModel() {
    private var pageNumber: MutableLiveData<Int> = MutableLiveData()
    var loading: ObservableBoolean = ObservableBoolean(true)
    private val nowPlayingMoviesList: LiveData<List<Movies>>
    val popularMoviesList: LiveData<List<Movies>>
    private val topRatedMoviesList: LiveData<List<Movies>>


    init {
        loading.set(true)

        popularMoviesList = pageNumber.switchMap {
            launchOnViewModelScope {
                this.moviesRepository.fetchPopularMoviesList(page = it, onSuccess = {
                    Timber.d("Success while fetching the data ")
                    loading.set(false)
                }, onError = {
                    Timber.e("An error occurred $it")
                    loading.set(true)
                })
            }
        }


        nowPlayingMoviesList = pageNumber.switchMap {
            launchOnViewModelScope {
                this.moviesRepository.fetchNowPlayingMovies(page = it,
                    onSuccess = {
                        loading.set(false)
                        Timber.d("Success")
                    }, onError = {
                        loading.set(true)
                        Timber.e("An error occurred: $it")
                    })
            }
        }

        topRatedMoviesList = pageNumber.switchMap {
            launchOnViewModelScope {
                this.moviesRepository.fetchTopRatedMoviesList(page = it, onSuccess = {
                    loading.set(false)
                    Timber.d("Success")
                },
                    onError = {
                        Timber.e("An error occurred:  $it")
                        loading.set(true)
                    })
            }
        }

    }


    fun setPageNumber(page:Int){
        pageNumber.value= page
    }
}