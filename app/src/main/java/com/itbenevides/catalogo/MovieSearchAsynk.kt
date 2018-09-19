package com.itbenevides.catalogo

import android.content.Context
import android.os.AsyncTask
import android.util.Log

open class MovieSearchAsynk(query:String,page:Int,context: Context,dataMovieReturn: DataMovieReturnInterface, movies:MutableList<Movie>) : AsyncTask<Void, Void, String>() {

    var movies: MutableList<Movie>? = movies
    var query: String? = query
    var context:Context? = context
    var dataMovieReturn:DataMovieReturnInterface? = dataMovieReturn
    var page:Int = page
    var result:Result?= Result();

    override fun doInBackground(vararg params: Void?): String? {


        val movieService = MovieService(context!!)

        // val detail = movieService.getDetail("tt0096895")

        try {
                result=movieService.search(query!!, page)
                movies?.addAll(result?.Search!!)
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
        dataMovieReturn?.dataMovieReturn(movies, this.result?.totalResults!!)
    }


}