package com.itbenevides.catalogo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieInterface {
    @GET("?")
    fun getSearch(@Query("apikey") apikey: String,@Query("s") searchText: String, @Query("page") pages: Int): Call<Result>


    @GET("?plot=full")
    fun getDetail(@Query("apikey") apikey: String,@Query("i") id: String): Call<Detail>

}