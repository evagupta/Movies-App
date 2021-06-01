package com.backbase.assignment.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.model.Movies
import com.backbase.assignment.ui.view.MoviesDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import me.turkergoksu.lib.PercentageView

class MoviesAdapter(val context: Context, private val items: List<Movies.Results>?) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        holder.title?.text = items?.get(position)?.title
        holder.releaseDate?.text = items?.get(position)?.release_date

        val perc: Float? = items?.get(position)?.rating?.toFloat()?.div(10)?.times(100)
        holder.rating?.setPercentage(perc?.toInt()!!)

        //Code for caching thru Glide
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original/${items?.get(position)?.poster_path}")
            .apply(requestOptions).into(holder.poster)

        // Define click listener for the ViewHolder's View.

        holder.layout?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, MoviesDetailActivity::class.java)
                intent.putExtra("id", items?.get(position)?.id)
                context.startActivity(intent)
            }
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items!!.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById(R.id.poster) as? ImageView
        val title = itemView.findViewById(R.id.title) as? TextView
        val releaseDate = itemView.findViewById(R.id.releaseDate) as? TextView
        val rating = itemView.findViewById(R.id.percentageView) as? PercentageView
        val layout = itemView.findViewById(R.id.layout) as? ConstraintLayout
    }
}