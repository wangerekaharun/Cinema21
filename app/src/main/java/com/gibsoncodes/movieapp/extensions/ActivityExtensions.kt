package com.gibsoncodes.movieapp.extensions

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.gibsoncodes.movieapp.R
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

// get a container for the material arc transformation
internal fun getContentTransform(context: Context): MaterialContainerTransform {
    return MaterialContainerTransform().apply {
        addTarget(android.R.id.content)
        duration = 450
        pathMotion = MaterialArcMotion()
        isElevationShadowEnabled = true
        startElevation = 9f
        endElevation = 9f
        startContainerColor = ContextCompat.getColor(
            context,
            R.color.colorPrimary
        )

    }
}

// exit transformation
fun AppCompatActivity.applyExitTransformation() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementsUseOverlay = false
}

// enter transformation
fun AppCompatActivity.applyEnterTransformation(transitionName: String) {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    ViewCompat.setTransitionName(
        findViewById(android.R.id.content),
        transitionName
    )
    // shared element transition
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = getContentTransform(this)
    window.sharedElementReturnTransition = getContentTransform(this)
}