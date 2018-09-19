package com.itbenevides.catalogo

class Movie {
    var Title: String? = null
    var Year: String? = null
    var imdbID: String? = null
    var Type: String? = null
    var Poster: String? = null

    override fun toString(): String {
        return "\nMovie{" +
                "Title='" + Title + '\''.toString() +
                ", Year='" + Year + '\''.toString() +
                ", imdbID='" + imdbID + '\''.toString() +
                ", Type='" + Type + '\''.toString() +
                ", Poster='" + Poster + '\''.toString() +
                '}'.toString()
    }

}
