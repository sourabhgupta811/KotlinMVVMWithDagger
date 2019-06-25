package com.example.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.repository.network.models.MoviesList

/**
 * Created by Sourabh Gupta on 25/6/19.
 */

@Dao
interface MovieListDao{
    @Query("Select * from movie_list")
    fun getMovieList(): LiveData<List<MoviesList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(moviesList: MoviesList)
}
