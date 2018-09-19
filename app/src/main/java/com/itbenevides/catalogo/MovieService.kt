package com.itbenevides.catalogo

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService(ctx: Context) {
    private val service: MovieInterface;
    private val ctx: Context = ctx;


    init {





        val retrofit = Retrofit.Builder()
                .baseUrl(ctx.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(MovieInterface::class.java)
    }

    fun search(txt: String, page: Int): Result? {

        val call = service.getSearch(ctx.resources.getString(R.string.api_key), txt, page)

        return call.execute().body()
    }

    fun getDetail(id:String): Detail?{

        val call= service.getDetail(ctx.resources.getString(R.string.api_key),id)

        return call.execute().body();

    }


}