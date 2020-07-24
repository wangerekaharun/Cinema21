package com.gibsoncodes.movieapp.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration constructor(private val includeEdge:Boolean,private val margin:Int,
                                     private val spanCount:Int):RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position%spanCount
        if (includeEdge){
            outRect.left=margin- column* margin/spanCount
            outRect.right = (column+1) * margin/spanCount
            if (position<spanCount)
                outRect.top= margin
            outRect.bottom=margin
        }else{
            outRect.left = column* margin/spanCount
            outRect.right=margin-(column+1)* margin/spanCount
            if (position>=spanCount){
                outRect.top=margin
            }
        }


    }

}