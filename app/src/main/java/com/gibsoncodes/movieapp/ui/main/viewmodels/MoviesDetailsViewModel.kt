package com.gibsoncodes.movieapp.ui.main.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.gibsoncodes.movieapp.base.BaseViewModel
import com.gibsoncodes.movieapp.model.MovieDetails
import com.gibsoncodes.movieapp.repository.MoviesRepository
import timber.log.Timber

class MoviesDetailsViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {
    private var movieId: MutableLiveData<Int> = MutableLiveData()
    val isLoading: ObservableBoolean = ObservableBoolean(true)

    fun setMovieId(id: Int) {
        movieId.value = id
    }

    val movieDetails: LiveData<MovieDetails>

    init {
        isLoading.set(true)
        movieDetails = movieId.switchMap {
            launchOnViewModelScope {
                this.moviesRepository.fetchMovieDetails(it, onError = {
                    isLoading.set(true)
                    Timber.e("An error occurred while fetching the movie details $it")
                }, onSuccess = {
                    isLoading.set(false)
                    Timber.e("Success while fetching the movie details ")
                })
            }
        }
    }


}