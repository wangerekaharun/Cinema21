package com.gibsoncodes.movieapp.ui.details

import android.os.Bundle
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModelProvider
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingActivity
import com.gibsoncodes.movieapp.databinding.ActivityDetailBinding
import com.gibsoncodes.movieapp.ui.main.viewmodels.MoviesDetailsViewModel
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : BaseDataBindingActivity() {
    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieDetailsViewModel: MoviesDetailsViewModel = ViewModelProvider(this).get(
            MoviesDetailsViewModel::class.java
        )
        val movieId = intent.getIntExtra("movieId", 547016)

        binding.detailContainerView.transitionName = movieId.toString()

        binding.apply {
            lifecycleOwner = this@MovieDetailsActivity
            movieDetailsVm = movieDetailsViewModel.apply {
                setMovieId(movieId)
            }

        }
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = buildContainerTransform()
        window.sharedElementReturnTransition = buildContainerTransform()


    }

    private fun buildContainerTransform() = MaterialContainerTransform().apply {
        addTarget(binding.detailContainerView)
        duration = 300
        interpolator = FastOutSlowInInterpolator()
        fadeMode = MaterialContainerTransform.FADE_MODE_IN
    }


}