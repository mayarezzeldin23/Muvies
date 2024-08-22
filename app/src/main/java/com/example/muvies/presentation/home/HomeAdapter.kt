package com.example.muvies.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.muvies.database.Movies
import com.example.muvies.databinding.MovieListItemBinding
import com.example.muvies.utils.setImageUrl

class HomeAdapter(
    private val movies: List<Movies>,
    private val onEditMovie: (Movies) -> Unit,
    private val onDeleteMovie: (Movies) -> Unit,
    private val onMovieClick: (Movies) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ItemViewHolder {
        return ItemViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.ItemViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.title.text = movie.title
        holder.binding.cast.text = movie.star
        setImageUrl(holder.binding.poster, movie.poster_path)
        holder.binding.delete.setOnClickListener {
            onDeleteMovie(movie)
        }
        holder.binding.edit.setOnClickListener {
            onEditMovie(movie)
        }

        holder.binding.root.setOnClickListener {
            onMovieClick(movie)
        }
        Log.i("onBind", "onBind")


    }

    override fun getItemCount(): Int {
        return movies.size
    }
}