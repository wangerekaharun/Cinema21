package com.gibsoncodes.movieapp.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gibsoncodes.movieapp.common.RecyclerViewCustomPaginator
import com.gibsoncodes.movieapp.model.Movies
import com.gibsoncodes.movieapp.model.Shows
import com.gibsoncodes.movieapp.ui.adapter.MoviesAdapter
import com.gibsoncodes.movieapp.ui.adapter.ShowsAdapter
import com.gibsoncodes.movieapp.ui.main.viewmodels.MainScreenBaseViewModel
import com.gibsoncodes.movieapp.ui.main.viewmodels.MoviesViewModel
import com.gibsoncodes.movieapp.ui.main.viewmodels.ShowsViewModel
import com.skydoves.whatif.whatIfNotNullOrEmpty
import timber.log.Timber

@BindingAdapter("vpAdapter")
fun bindAdapter(view:ViewPager2,adapter:RecyclerView.Adapter<*>){
 view.adapter= adapter

}
@BindingAdapter("moviesAdapter")
fun bindMoviesAdapter(view:RecyclerView,adapter:RecyclerView.Adapter<*>){
    view.adapter=adapter
}
@BindingAdapter("showsAdapter")
fun bindShowsAdapter(view:RecyclerView,adapter: RecyclerView.Adapter<*>){
    view.adapter=adapter
}
@BindingAdapter("moviesRvList")
fun bindMoviesRvList(recyclerView: RecyclerView,list: List<Movies>?){

    list.whatIfNotNullOrEmpty {
        Timber.e("Size $it.size")
        (recyclerView.adapter as MoviesAdapter).addItems(it)
    }
}

@BindingAdapter("showsRvList")
fun bindShowsRvList(recyclerView: RecyclerView,list:List<Shows>?){
    Timber.e("Size $list.size")
    list.whatIfNotNullOrEmpty {
        (recyclerView.adapter as ShowsAdapter).addItems(it)
    }

}


@BindingAdapter("paginatedMainScreenList")
fun bindPaginatedMainScreenList(rv: RecyclerView, mainScreenViewModel: MainScreenBaseViewModel) {
    RecyclerViewCustomPaginator(recyclerView = rv,
        isLoading = { mainScreenViewModel.isLoading.get() },
        loadMore = { mainScreenViewModel.setPage(1) },
        onLast = { false }
    ).run {
        threshold = 2
    }
}

@BindingAdapter("paginatedMovies")
fun bindingPaginatedMovies(rv: RecyclerView, moviesVm: MoviesViewModel) {
    RecyclerViewCustomPaginator(recyclerView = rv,
        isLoading = { moviesVm.loading.get() },
        loadMore = { moviesVm.setPageNumber(1) },
        onLast = { false }
    ).run {
        threshold = 2
    }
}

@BindingAdapter("paginatedMainScreenDataList")
fun bindingPaginatedMainScreen(rv: RecyclerView, mainScreenVm: MainScreenBaseViewModel) {
    RecyclerViewCustomPaginator(recyclerView = rv,
        isLoading = { mainScreenVm.isLoading.get() },
        loadMore = { mainScreenVm.setPage(it) },
        onLast = { false }
    ).run {
        threshold = 2
    }
}

@BindingAdapter("paginatedShows")
fun bindingPaginatedShows(rv: RecyclerView, showsVm: ShowsViewModel) {
    RecyclerViewCustomPaginator(recyclerView = rv,
        isLoading = { showsVm.loading.get() },
        loadMore = { showsVm.setPage(it) },
        onLast = { false }
    ).run {
        threshold = 2
    }
}

