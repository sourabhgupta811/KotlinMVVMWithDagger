package com.sourabh.xplayer.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.repository.network.models.MoviesList
import com.example.repository.repo.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var _movieListLiveData: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val movieListLiveData: LiveData<List<MoviesList>>
        get() = _movieListLiveData
    lateinit var movieListLiveDataFromRespository: LiveData<List<MoviesList>>

    fun getMoviesList(context: Context) {
        val movieRepository = MovieRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            movieListLiveDataFromRespository = movieRepository.getMovies(6)
            launch(Dispatchers.Main) {
                movieListLiveDataFromRespository.observeForever{ movieList ->
                    _movieListLiveData.postValue(movieList)
                }
            }
        }
    }
}