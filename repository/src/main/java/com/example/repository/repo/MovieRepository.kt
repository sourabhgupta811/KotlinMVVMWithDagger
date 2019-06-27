package com.example.repository.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.repository.database.dao.MovieListDao
import com.example.repository.database.db.DatabaseKeeper
import com.example.repository.database.db.TMDBDatabase
import com.example.repository.network.api.TMDBApiKeeper
import com.example.repository.network.models.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Sourabh Gupta on 25/6/19.
 */
class MovieRepository(context: Context) {
    private var movieListDao: MovieListDao
    private val tmdbDatabase: TMDBDatabase = DatabaseKeeper.getDatabase(context).also {
        movieListDao  = it.movieListDao()
    }
    private val applicationContext = context.applicationContext
    private val tmdbApiKeeper: TMDBApiKeeper = TMDBApiKeeper
//    private val _movieListLiveData : MutableLiveData<List<MoviesList>> = MutableLiveData()
//    val movieListLiveData: LiveData<List<MoviesList>>
//    get() = _movieListLiveData

    suspend fun getMovies(page:Int): LiveData<List<MoviesList>>{
        return withContext(Dispatchers.IO) {
            getMoviesFromNetwork(page)
            return@withContext movieListDao.getMovieList()
        }
    }
    private suspend fun getMoviesFromNetwork(page:Int){
        movieListDao.insertMovieList(tmdbApiKeeper.getMoviesList(pageNumber = page))
    }
}