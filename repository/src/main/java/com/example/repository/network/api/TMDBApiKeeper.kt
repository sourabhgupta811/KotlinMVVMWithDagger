package com.example.repository.network.api

import com.example.repository.network.models.MoviesList

object TMDBApiKeeper{
    val tMDBApi = TMDBApi()

    suspend fun getMoviesList(sortBy:String = "popularity.desc", pageNumber:Int = 1): MoviesList {
        return tMDBApi.getMoviesList(sortBy,pageNumber)
    }
}