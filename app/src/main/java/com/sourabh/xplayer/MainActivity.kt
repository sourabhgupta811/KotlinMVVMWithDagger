package com.sourabh.xplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.repository.network.models.MoviesList
import com.example.repository.repo.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var movieListLiveData: LiveData<List<MoviesList>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var movieRepository = MovieRepository(this)
        CoroutineScope(Dispatchers.IO).launch {
            movieListLiveData = movieRepository.getMovies(6)
            launch(Dispatchers.Main) {
                movieListLiveData.observe(this@MainActivity, Observer { movieList ->
                    for(item in movieList) {
                        textView.append(item.toString())
                        textView.append("\n\n*******************************************************************************************************************************************************************************\n")
                    }
                })
            }
        }
    }
}
