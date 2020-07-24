package com.gibsoncodes.movieapp.ui.main.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.gibsoncodes.movieapp.base.BaseViewModel
import com.gibsoncodes.movieapp.model.Shows
import com.gibsoncodes.movieapp.repository.ShowsRepository
import timber.log.Timber

class ShowsViewModel @ViewModelInject constructor(private val showsRepository: ShowsRepository):BaseViewModel() {
    val popularShowsList:LiveData<List<Shows>>
    private val pageNumber:MutableLiveData<Int> = MutableLiveData()
    var loading:ObservableBoolean = ObservableBoolean(true)
    init {
        loading.set(true)
    popularShowsList=pageNumber.switchMap {
        launchOnViewModelScope {
            showsRepository.fetchPopularShowsList(it,onSuccess = {
                loading.set(false)
            },
            onError = {
                loading.set(false)
             Timber.e("An error occurred: $it")
            })
        }
    }
    }
    fun setPage(pageId:Int){
        pageNumber.value = pageId
    }
}