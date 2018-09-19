package com.itbenevides.catalogo




class Result {
    var Search: MutableList<Movie>? = null
    var totalResults: Long = 0
    var Response: String? = null



    override fun toString(): String {
        return "Result{" +
                "Search=" + Search +
                ", totalResults='" + totalResults + '\''.toString() +
                ", Response='" + Response + '\''.toString() +
                '}'.toString()
    }

}