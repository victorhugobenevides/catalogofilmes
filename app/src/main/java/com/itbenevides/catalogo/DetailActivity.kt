package com.itbenevides.catalogo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import com.itbenevides.catalogo.R.id.toolbar




class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        supportActionBar?.hide()
        val detail = intent.extras["movie"] as Detail

        toolbar.setNavigationOnClickListener { finish() }

        loadData(detail)


    }


    private fun loadData(detail: Detail) {

        year.text = detail.Year
        rated.text = detail.Rated
        release.text = detail.Released
        runtime.text = detail.Runtime
        genre.text = detail.Genre
        director.text = detail.Director
        writer.text = detail.Writer
        actors.text = detail.Actors
        plot.text = detail.Plot
        language.text = detail.Language
        country.text = detail.Country
        awards.text = detail.Awards
        metascore.text = detail.Metascore
        rating.text = detail.imdbRating
        votes.text = detail.imdbVotes
        type.text = detail.Type




        toolbar.title = detail.Title;
        Glide.with(applicationContext).load(detail.Poster).into(image)
    }

}
