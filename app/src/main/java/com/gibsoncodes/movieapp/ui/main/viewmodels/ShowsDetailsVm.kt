package com.gibsoncodes.movieapp.ui.main.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.gibsoncodes.movieapp.base.BaseViewModel
import com.gibsoncodes.movieapp.model.ShowsDetails
import com.gibsoncodes.movieapp.repository.ShowsRepository
import timber.log.Timber

class ShowsDetailsVm @ViewModelInject constructor(private val showsRepository: ShowsRepository) :
    BaseViewModel() {
    val isLoading: ObservableBoolean = ObservableBoolean(true)
    private var showId: MutableLiveData<Int> = MutableLiveData()
    val show: LiveData<ShowsDetails>

    init {
        isLoading.set(true)
        show = showId.switchMap {
            launchOnViewModelScope {
                this.showsRepository.fetchShowInfo(it,
                    onError = {
                        Timber.e("An error occurred")
                        isLoading.set(true)
                    },
                    onSuccess = {
                        isLoading.set(false)
                    })
            }
        }
    }

    fun setShowId(id: Int) {
        showId.value = id
    }
}

