package com.gibsoncodes.movieapp.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingFragment
import com.gibsoncodes.movieapp.common.GridItemDecoration
import com.gibsoncodes.movieapp.databinding.FragmentShowsBinding
import com.gibsoncodes.movieapp.extensions.dpToPx
import com.gibsoncodes.movieapp.ui.adapter.ShowsAdapter
import com.gibsoncodes.movieapp.ui.details.ShowsDetailsActivity
import com.gibsoncodes.movieapp.ui.main.viewmodels.ShowsViewModel
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowsFragment : BaseDataBindingFragment() {
    private lateinit var binding: FragmentShowsBinding
    private lateinit var viewModel: ShowsViewModel
    private val showsAdapter = ShowsAdapter()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShowsViewModel::class.java)
        binding.showsViewModel = viewModel.apply { setPage(1) }

        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.showsRv.layoutManager = gridLayoutManager
        binding.showsRv.addItemDecoration(
            GridItemDecoration(
                true,
                activity?.applicationContext!!.dpToPx(10),
                2
            )
        )
        showsAdapter.listenerCallback = object : ShowsAdapter.ShowOnClickListener {
            override fun onShowClicked(showId: Int, showImage: AppCompatImageView) {
                val intent = Intent(
                    activity?.applicationContext!!,
                    ShowsDetailsActivity::class.java
                )
                activity.whatIfNotNull {
                    val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
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
        binding =
            binding<FragmentShowsBinding>(inflater, container, R.layout.fragment_shows).apply {
                lifecycleOwner = this@ShowsFragment
                adapter = showsAdapter
            }
        return binding.root
    }


}