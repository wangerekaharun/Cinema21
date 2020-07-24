package com.gibsoncodes.movieapp.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingFragment
import com.gibsoncodes.movieapp.common.GridItemDecoration
import com.gibsoncodes.movieapp.databinding.FragmentMainBinding
import com.gibsoncodes.movieapp.extensions.dpToPx
import com.gibsoncodes.movieapp.ui.adapter.MoviesAdapter
import com.gibsoncodes.movieapp.ui.adapter.ShowsAdapter
import com.gibsoncodes.movieapp.ui.details.MovieDetailsActivity
import com.gibsoncodes.movieapp.ui.details.ShowsDetailsActivity
import com.gibsoncodes.movieapp.ui.main.viewmodels.MainScreenBaseViewModel
import com.gibsoncodes.movieapp.ui.main.viewmodels.MoviesViewModel
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


@AndroidEntryPoint
class MainFragment : BaseDataBindingFragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainScreenBaseViewModel
    private lateinit var movieModel: MoviesViewModel
    private val mMoviesAdapter = MoviesAdapter()
    private val mShowsAdapter = ShowsAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel = ViewModelProvider(this).get(MainScreenBaseViewModel::class.java)
        binding.mainScreenViewModel = viewModel

        val linearLayoutManager = ZoomRecyclerLayout(activity?.applicationContext!!)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.latestMovies.layoutManager = linearLayoutManager

        val gridLayoutManager = GridLayoutManager(activity?.applicationContext, 2)
        binding.latestShows.layoutManager = gridLayoutManager
        binding.latestShows.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        binding.latestShows.addItemDecoration(
            GridItemDecoration(
                true,
                activity?.applicationContext!!.dpToPx(10),
                2
            )
        )


        mMoviesAdapter.movieClickListener = object : MoviesAdapter.MovieClickListener {
            override fun onMovieClicked(movieId: Int, movieImage: AppCompatImageView) {
                val intent = Intent(
                    activity?.applicationContext!!,
                    MovieDetailsActivity::class.java
                )
                activity.whatIfNotNull {
                    val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
                            it,
                            movieImage, movieId.toString()
                        )
                    intent.putExtra("movieId", movieId)
                    startActivity(intent, options.toBundle())
                }

            }
        }
        mShowsAdapter.listenerCallback = object : ShowsAdapter.ShowOnClickListener {
            override fun onShowClicked(showId: Int, showImage: AppCompatImageView) {
                val intent =
                    Intent(activity?.applicationContext!!, ShowsDetailsActivity::class.java)
                activity?.whatIfNotNull {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        it,
                        showImage, showId.toString()
                    )
                    intent.putExtra("showId", showId)
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding<FragmentMainBinding>(inflater,container!!,R.layout.fragment_main).apply {
            moviesAdapter = mMoviesAdapter
            showsAdapter = mShowsAdapter
            lifecycleOwner = this@MainFragment
        }
        return binding.root
    }


}