package com.gibsoncodes.movieapp.binding

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gibsoncodes.movieapp.BuildConfig
import com.gibsoncodes.movieapp.common.CircleProgressBar
import com.gibsoncodes.movieapp.common.CornerType
import com.gibsoncodes.movieapp.common.RoundedCornerTransformation
import com.gibsoncodes.movieapp.model.Genre
import com.gibsoncodes.movieapp.model.ShowsDetails
import com.gibsoncodes.movieapp.ui.adapter.MoviesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.squareup.picasso.Picasso
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlin.math.roundToInt

@BindingAdapter("transformFab","transformContainer")
fun bindFabButtonTransformation(view:View,fab:FloatingActionButton,
containerView:CoordinatorLayout){
    fab.setOnClickListener {
        fab.tag=View.GONE
        android.transition.TransitionManager.beginDelayedTransition(containerView,
        getTransformation(fab,view))
        fab.visibility=View.GONE
        view.visibility=View.VISIBLE
    }
    view.setOnClickListener {
        fab.tag = View.VISIBLE
        android.transition.TransitionManager.beginDelayedTransition(
            containerView,
            getTransformation(view, fab)
        )
        fab.visibility = View.VISIBLE
        view.visibility = View.GONE
    }

}


internal fun getTransformation(mStartView: View, mEndView: View): MaterialContainerTransform {
    return MaterialContainerTransform().apply {

        interpolator = FastOutSlowInInterpolator()
        fadeMode = MaterialContainerTransform.FADE_MODE_IN
        startView = mStartView
        endView = mEndView
        duration = 800
        scrimColor = Color.TRANSPARENT

    }

}

@SuppressLint("SetTextI18n")
@BindingAdapter("moviesAverageCount")
fun bindMoviesAverageCount(textV: AppCompatTextView, voteAverage: Double) {
    voteAverage.whatIfNotNull {
        val finalVoteCount = it.roundToInt().toFloat()
        textV.text = "$finalVoteCount "
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("inProduction")
fun bindShowInProductions(textV: AppCompatTextView, value: Boolean) {
    textV.text = "$value"

}

@SuppressLint("SetTextI18n")
@BindingAdapter("moviesPopularityCount")
fun bindMoviesPopularityCount(progress: CircleProgressBar, popularity: Double) {
    popularity.whatIfNotNull {
        val finalPopularity = it.roundToInt().toFloat()
        progress.setProgress(finalPopularity)
        progress.setMin(0)
        progress.setMax(100)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("moviesRuntime")
fun bindMoviesRuntime(textV: AppCompatTextView, runTime: Double) {
    runTime.whatIfNotNull {
        textV.text = "Movie runtime $it . mins "
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("episodeRuntime")
fun bindEpisodeRuntime(textV: AppCompatTextView, runTime: List<Double>?) {
    runTime.whatIfNotNullOrEmpty {
        val episode = it[0]
        textV.text = "Episode runtime $episode . mins "
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("createdBy")
fun bindCreatedBy(textV: AppCompatTextView, createdBy: List<ShowsDetails.CreatedBy>?) {
    createdBy.whatIfNotNullOrEmpty {
        val name = it[0]
        textV.text = "created by ${name.name}  "
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("movieGenre")
fun bindMovieGenre(textV: AppCompatTextView, genre: List<Genre>?) {
    genre.whatIfNotNullOrEmpty {
        val genreName = it[0]
        textV.text = " ${genreName.genreName}  "
    }
}


@BindingAdapter("showsPosterImage")
fun bindShowsPosterImage(view: ImageView, imageUrl: String) {
    val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/w500${imageUrl}"
    Picasso.with(view.context)
        .load(fullImagePosterPath)
        .fit()
        .centerCrop()
        .transform(RoundedCornerTransformation(5, 0, CornerType.TOP_LEFT))
        .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_LEFT))
        .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_RIGHT))
        .transform(RoundedCornerTransformation(20, 0, CornerType.TOP_RIGHT))
        .into(view)
}

@BindingAdapter("movieDetailsBackdropImage")
fun bindMovieDetailsBackdropImage(view: AppCompatImageView, imageUrl: String) {
    val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/w500${imageUrl}"
    Picasso.with(view.context)
        .load(fullImagePosterPath)
        .fit()
        .centerCrop()
        .transform(RoundedCornerTransformation(15, 0, CornerType.ALL))
        .into(view)
}

@BindingAdapter("overviewFormat")
fun bindOverviewFormat(view: AppCompatTextView, text: String?) {
    text.whatIfNotNull {
        val spannableStringBuilder = SpannableStringBuilder(it)
        spannableStringBuilder.setSpan(
            RelativeSizeSpan(4f),
            0,
            1,
            SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannableStringBuilder
    }

}

@BindingAdapter("movieDetailsImage")
fun bindMovieDetailsImage(view: AppCompatImageView, imageUrl: String?) {
    imageUrl.whatIfNotNull {
        val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/original${it}"
        Picasso.with(view.context)
            .load(fullImagePosterPath)
            .fit()
            .centerCrop()
            .transform(RoundedCornerTransformation(20, 0, CornerType.ALL))
            .into(view)
    }

}

@BindingAdapter("showsDetailsImage")
fun bindShowsDetailsImage(view: AppCompatImageView, imageUrl: String?) {
    imageUrl.whatIfNotNull {
        val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/original${it}"
        Picasso.with(view.context)
            .load(fullImagePosterPath)
            .fit()
            .centerCrop()
            .transform(RoundedCornerTransformation(20, 0, CornerType.ALL))
            .into(view)
    }

}

@BindingAdapter("movieDetailsThumbnail")
fun bindMovieDetailsThumbnail(view: AppCompatImageView, imageUrl: String?) {
    imageUrl.whatIfNotNull {
        val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/w500${it}"
        Picasso.with(view.context)
            .load(fullImagePosterPath)
            .fit()
            .centerCrop()
            .transform(RoundedCornerTransformation(5, 0, CornerType.TOP_LEFT))
            .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_LEFT))
            .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_RIGHT))
            .transform(RoundedCornerTransformation(20, 0, CornerType.TOP_RIGHT))
            .into(view)
    }

}

@BindingAdapter("showsDetailThumbnail")
fun bindShowsDetailsThumbnail(view: AppCompatImageView, imageUrl: String?) {
    imageUrl.whatIfNotNull {
        val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/w500${it}"
        Picasso.with(view.context)
            .load(fullImagePosterPath)
            .fit()
            .centerCrop()
            .transform(RoundedCornerTransformation(5, 0, CornerType.TOP_LEFT))
            .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_LEFT))
            .transform(RoundedCornerTransformation(5, 0, CornerType.BOTTOM_RIGHT))
            .transform(RoundedCornerTransformation(20, 0, CornerType.TOP_RIGHT))
            .into(view)
    }

}

@BindingAdapter("paletteMoviesPosterImage")
fun bindMoviesPosterImage(view: AppCompatImageView, imageUrl: String) {
    val fullImagePosterPath = "${BuildConfig.BASE_IMAGE_URL}/w500${imageUrl}"
    Picasso.with(view.context)
        .load(fullImagePosterPath)
        .fit()
        .centerCrop()
        .transform(RoundedCornerTransformation(15, 0, CornerType.ALL))
        .into(view)
}

@BindingAdapter("gone")
fun bindProgressBar(view: View, dissapear: Boolean) {
    if (dissapear) view.visibility = View.GONE
    else view.visibility = View.VISIBLE
}


@BindingAdapter("moviesImageBackground")
fun bindBackdropMoviesImage(view: AppCompatImageView, recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        @SuppressLint("ResourceAsColor")
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // get the view
                    val show =
                        (recyclerView.adapter as MoviesAdapter).returnMovieItem(position = currentPosition)
                    val imageBackdropPath = "${BuildConfig.BASE_IMAGE_URL}/w500${show?.posterPath}"
                    //val preLoadSizePreloader:ViewPreloadSizeProvider<Movies> = ViewPreloadSizeProvider()
                    // val preloader:RecyclerViewPreloader<Movies> = RecyclerViewPreloader(view.context,
                    // recyclerView.adapter,preLoadSizePreloader,5)
                    // find a wau to pre cache the images
                    Glide.with(view.context)
                        .load(imageBackdropPath)
                        .fitCenter()
                        .placeholder(ColorDrawable(android.R.color.transparent))
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(15)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view)
                }
            }
        }
    })
}






