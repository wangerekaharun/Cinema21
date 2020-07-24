package com.gibsoncodes.movieapp.ui.details

import android.os.Bundle
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModelProvider
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingActivity
import com.gibsoncodes.movieapp.databinding.ActivityShowsDetailsBinding
import com.gibsoncodes.movieapp.ui.main.viewmodels.ShowsDetailsVm
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsDetailsActivity : BaseDataBindingActivity() {
    val binding: ActivityShowsDetailsBinding by binding(R.layout.activity_shows_details)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ShowsDetailsVm = ViewModelProvider(this).get(
            ShowsDetailsVm::class.java
        )
        val showId = intent.getIntExtra("showId", 0)
        binding.detailContainerView.transitionName = showId.toString()
        binding.apply {
            lifecycleOwner = this@ShowsDetailsActivity
            showsDetailsVm = viewModel.apply {
                setShowId(showId)
            }
        }
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = buildContainerTransform()
        window.sharedElementReturnTransition = buildContainerTransform()
    }

    private fun buildContainerTransform() = MaterialContainerTransform().apply {
        addTarget(binding.detailContainerView)
        pathMotion = MaterialArcMotion()
        duration = 300
        interpolator = FastOutSlowInInterpolator()
        fadeMode = MaterialContainerTransform.FADE_MODE_IN
    }
}