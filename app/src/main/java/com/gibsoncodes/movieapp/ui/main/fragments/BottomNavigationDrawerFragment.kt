package com.gibsoncodes.movieapp.ui.main.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gibsoncodes.movieapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_navbar_layout.*


@AndroidEntryPoint
class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    companion object {
        const val mainFragment: String = "mainFragment"
        const val showsFragment = "showsFragment"
        const val moviesFragment = "moviesFragment"
        const val bottomNavigationDrawerFragmentTag = "bottomNavigationDrawerFragmentTag"
    }

    var listener: OnCategorySelectedListener? = null

    interface OnCategorySelectedListener {
        fun onCategorySelected(categoryName: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnCategorySelectedListener
        if (listener == null) {
            throw java.lang.ClassCastException("The activity must implement this interface")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_navbar_layout,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (view?.parent as View).setBackgroundColor(Color.TRANSPARENT)
        navigation_view.setNavigationItemSelectedListener {
           when(it.itemId){
                R.id.shows_nav->{
                    listener?.onCategorySelected(showsFragment)
                    true
                }
                R.id.movies_nav->{
                    listener?.onCategorySelected(moviesFragment)
                    true
                }
               R.id.main_nav->{
                   listener?.onCategorySelected(mainFragment)
                   true
               }
                else->{
                    false
                }
            }
        }
    }



}