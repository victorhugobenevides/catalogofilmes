package com.itbenevides.catalogo

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log

open class DetailMovieSearchAsynk(context: Context, private val dataMovieReturn: DataMovieReturnInterface, movies:Movie) : AsyncTask<Void, Void, String>() {

    var movies: Movie? = movies
    var context:Context? = context
    var detail:Detail?= null;

    override fun doInBackground(vararg params: Void?): String? {


        val movieService = MovieService(context!!)

        // val detail = movieService.getDetail("tt0096895")

        try {
            detail=movieService.getDetail(movies?.imdbID!!)

        }catch (e:Exception){
            Log.d("ERRO",e.toString());
        }



        return null

    }

    override fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        dataMovieReturn?.dataDetailMovieReturn(detail)

    }


}