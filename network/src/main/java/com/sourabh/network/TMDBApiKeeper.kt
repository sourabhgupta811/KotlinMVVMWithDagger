package com.sourabh.network

import com.sourabh.network.models.MoviesList

object TMDBApiKeeper{
    val tMDBApi = TMDBApi()

    suspend fun getMoviesList(sortBy:String = "popularity.desc", pageNumber:Int = 1): MoviesList {
        return tMDBApi.getMoviesList(sortBy,pageNumber)
    }
}