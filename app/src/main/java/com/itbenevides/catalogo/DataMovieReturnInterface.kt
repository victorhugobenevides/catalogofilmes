package com.itbenevides.catalogo

interface DataMovieReturnInterface{
    fun dataMovieReturn(list: MutableList<Movie>?, totalItem: Long)
    fun dataDetailMovieReturn(detail: Detail?)
}