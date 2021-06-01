package com.backbase.assignment.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.model.PosterImage
import com.bumptech.glide.Glide

class PicturesAdapter(val context: Context, private val items: List<PosterImage.Results>?) :
    RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pictures_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original/${items?.get(position)?.poster_path}")
            .into(holder.poster)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items!!.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val poster = itemView.findViewById(R.id.horizontal_poster_images) as? ImageView
    }
}