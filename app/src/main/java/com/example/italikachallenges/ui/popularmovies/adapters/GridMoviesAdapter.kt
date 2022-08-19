package com.example.italikachallenges.ui.popularmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.italikachallenges.databinding.GridListItemMovieBinding
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.utis.Const

class GridMoviesAdapter(private var movies: List<Movie>,
                        val movieSelectedCallback: (Movie) -> Unit) :
    RecyclerView.Adapter<GridMoviesAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: GridListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = GridListItemMovieBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies.get(position)
        with(holder) {
            with(binding) {
                Glide.with(mContext)
                    .load(Const.IMAGE_URL + movie.posterPath)
                    .into(imageMovie)

                textTitle.text = movie.title

                imageMovie.setOnClickListener {
                    movieSelectedCallback(movie)
                }
            }
        }

    }


    override fun getItemCount(): Int = movies.size


}