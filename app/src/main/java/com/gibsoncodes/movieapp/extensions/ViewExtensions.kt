package com.gibsoncodes.movieapp.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.transition.Slide
import com.gibsoncodes.movieapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

// similar to the original snack bar code btw
internal fun View?.findSuitableParent():ViewGroup?{
    var view=this
    var fallback:ViewGroup?=null
    do{
        if (view is CoordinatorLayout){
            // coordinator layout
            return view
        }else if (view is FrameLayout){
            if (view.id==android.R.id.content){
                // since we do not have a coordinator layout use the frame layout provided
                // it is the content view
                return view
                // not the content view but its still a frame layout so use it
            }else fallback=view
        }
        // since there is no frame layout
        if (view!=null){
            // go up the hierarchy and get the parent
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    }while (view!=null)

    return fallback
}

fun View.gone(disappear: Boolean) {
    visibility = when {
        !disappear -> {
            View.GONE
        }
        else -> {
            View.VISIBLE
        }
    }
}

fun Context.dpToPx(dp: Int): Int {
    val resources = resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp.toFloat()
        , resources.displayMetrics
    ).roundToInt()
}

@SuppressLint("NewApi")
fun showSnack(
    context: Context, parentView: View, fab: FloatingActionButton,
    msg: String
) {
    val snack = Snackbar.make(
        parentView,
        msg, Snackbar.LENGTH_LONG
    )
    snack.apply {
        setTextColor(context.getColor(android.R.color.white))
        setBackgroundTint(context.getColor(R.color.colorPrimary))
        anchorView = fab
    }.show()

}

fun View.toggleButton(show:Boolean,parentView:ViewGroup){
    val transition:androidx.transition.Transition= Slide(Gravity.BOTTOM)
    transition.duration = 600
    transition.addTarget(this)
    androidx.transition.TransitionManager.beginDelayedTransition(
        parentView, transition)
    if (!show){
        this.animate().alpha(0f).setDuration(400L)
            .setListener(object:AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    visibility=View.GONE
                }
            })

    }else{
        this.animate()
            .alpha(1f)
            .setDuration(400L)
            .setListener(object:AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    visibility=View.VISIBLE
                }
            })
    }
}