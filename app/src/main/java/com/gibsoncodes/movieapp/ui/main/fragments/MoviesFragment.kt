package com.gibsoncodes.movieapp.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingFragment
import com.gibsoncodes.movieapp.databinding.FragmentMoviesBinding
import com.gibsoncodes.movieapp.ui.adapter.MoviesAdapter
import com.gibsoncodes.movieapp.ui.details.MovieDetailsActivity
import com.gibsoncodes.movieapp.ui.main.viewmodels.MoviesViewModel
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

@AndroidEntryPoint
class MoviesFragment : BaseDataBindingFragment() {
    private lateinit var binding: FragmentMoviesBinding
    private var viewModel: MoviesViewModel? = null

    private val adapter_: MoviesAdapter = MoviesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding<FragmentMoviesBinding>(
            inflater,
            container!!, R.layout.fragment_movies
        ).apply {
            lifecycleOwner = this@MoviesFragment
            adapter = adapter_
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activity.whatIfNotNull {
            it.setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        }
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rv = binding.moviesRv
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)


        binding.movieVm = viewModel?.apply { viewModel?.setPageNumber(2) }
        val snapHelper = LinearSnapHelper()
        val linearLayoutManager = ZoomRecyclerLayout(activity?.applicationContext!!)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.moviesRv.layoutManager = linearLayoutManager
        snapHelper.attachToRecyclerView(binding.moviesRv)
        adapter_.movieClickListener = object : MoviesAdapter.MovieClickListener {
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

    }


}


