package com.itbenevides.catalogo

import org.intellij.lang.annotations.Language
import java.io.Serializable


class Detail: Serializable {
    
    var Title: String? = null
    var Year: String? = null
    var Rated: String? = null
    var Released: String? = null
    var Runtime: String? = null
    var Genre: String? = null
    var Director: String? = null
    var Writer: String? = null
    var Actors: String? = null
    var Plot: String? = null
    var Language: String? = null
    var Country: String? = null
    var Awards: String? = null
    var Poster: String? = null
    var Metascore: String? = null
    var imdbRating: String? = null
    var imdbVotes: String? = null
    var imdbID: String? = null
    var Type: String? = null
    var Response: String? = null

    override fun toString(): String {
        return "Detail{" +
                "Title='" + Title + '\''.toString() +
                ", Year='" + Year + '\''.toString() +
                ", Rated='" + Rated + '\''.toString() +
                ", Released='" + Released + '\''.toString() +
                ", Runtime='" + Runtime + '\''.toString() +
                ", Genre='" + Genre + '\''.toString() +
                ", Director='" + Director + '\''.toString() +
                ", Writer='" + Writer + '\''.toString() +
                ", Actors='" + Actors + '\''.toString() +
                ", Plot='" + Plot + '\''.toString() +
                ", Language='" + Language + '\''.toString() +
                ", Country='" + Country + '\''.toString() +
                ", Awards='" + Awards + '\''.toString() +
                ", Poster='" + Poster + '\''.toString() +
                ", Metascore='" + Metascore + '\''.toString() +
                ", imdbRating='" + imdbRating + '\''.toString() +
                ", imdbVotes='" + imdbVotes + '\''.toString() +
                ", imdbID='" + imdbID + '\''.toString() +
                ", Type='" + Type + '\''.toString() +
                ", Response='" + Response + '\''.toString() +
                '}'.toString()
    }

   
}