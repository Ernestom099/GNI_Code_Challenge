package com.example.italikachallenges.ui.popularmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.italikachallenges.databinding.GridListItemGalleryBinding
import com.example.italikachallenges.databinding.GridListItemMovieBinding
import com.example.italikachallenges.models.Image
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.utis.Const

class GridGalleryAdapter(
    private var images: List<Image>
) :
    RecyclerView.Adapter<GridGalleryAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: GridListItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding =
            GridListItemGalleryBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images.get(position)
        with(holder) {
            with(binding) {
                Glide.with(mContext)
                    .load(image.url)
                    .centerCrop()
                    .into(imageGallery)
            }
        }

    }


    override fun getItemCount(): Int = images.size


}