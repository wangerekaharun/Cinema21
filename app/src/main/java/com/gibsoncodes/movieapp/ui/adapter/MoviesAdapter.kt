package com.gibsoncodes.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.databinding.MoviesItemBinding
import com.gibsoncodes.movieapp.model.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val moviesList: MutableList<Movies> = mutableListOf()

    lateinit var movieClickListener: MovieClickListener

    interface MovieClickListener {
        fun onMovieClicked(movieId: Int, movieImage: AppCompatImageView)
    }

    inner class ViewHolder(val binding: MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            binding.movies = movie
            binding.moviesImageBanner.transitionName = movie.id.toString()
            binding.executePendingBindings()
            binding.moviesImageBanner.setOnClickListener {
                movieClickListener.onMovieClicked(
                    movie.id,
                    binding.moviesImageBanner
                )
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<MoviesItemBinding>(
            layoutInflater,
            R.layout.movies_item,
            parent,
            false
        )
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun addItems(list: List<Movies>) {
        moviesList.addAll(list)
        notifyDataSetChanged()
    }

    fun returnMovieItem(position: Int): Movies? {
        return moviesList[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.bind(item)
        holder.binding.apply {
            movies = item
        }
    }
}