package com.gibsoncodes.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.databinding.ShowsItemBinding
import com.gibsoncodes.movieapp.model.Shows

class ShowsAdapter:RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder>() {
    private val showsList: MutableList<Shows> = mutableListOf()
    lateinit var listenerCallback: ShowOnClickListener

    interface ShowOnClickListener {
        fun onShowClicked(showId: Int, showImage: AppCompatImageView)
    }

    inner class ShowsViewHolder(val binding: ShowsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shows: Shows) {
            binding.shows = shows
            binding.showsImage.transitionName = shows.id.toString()
            binding.executePendingBindings()
            binding.showsImage.setOnClickListener {
                listenerCallback.onShowClicked(
                    shows.id,
                    binding.showsImage
                )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ShowsItemBinding>(inflater, R.layout.shows_item, parent, false)
        return ShowsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return showsList.size
    }
   fun addItems(list:List<Shows>){
       showsList.addAll(list)
       notifyDataSetChanged()
   }
    fun getShow(position:Int):Shows?{
        return showsList[position]
    }
    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        val item = showsList[position]
        holder.bind(item)
        holder.binding.apply {
            shows = item

        }
    }

}