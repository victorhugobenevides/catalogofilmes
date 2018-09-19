package com.itbenevides.catalogo

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(private val movies: List<Movie>,
                      private val context: Context,private val onClickInterface:OnClickInterface) : Adapter<MovieAdapter.ViewHolder>() {





    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val movie = movies[position]
        holder?.let {
            it.bindView(movie,context,onClickInterface)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie,context: Context, onClickInterface: OnClickInterface) {
            val title = itemView.title
            val year = itemView.year
            val type = itemView.type
            val image = itemView.image

            title.text = movie.Title
            year.text = movie.Year
            type.text = movie.Type


            Glide.with(context).load(movie.Poster).into(image)

            itemView.setOnClickListener {

                onClickInterface.onClick(itemView,movie)


            }


        }

    }

}