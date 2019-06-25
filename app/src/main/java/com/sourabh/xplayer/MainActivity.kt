package com.sourabh.xplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sourabh.network.TMDBApiKeeper
import com.sourabh.network.models.MoviesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            var moviesList : MoviesList? = null
            try {
                moviesList = TMDBApiKeeper.getMoviesList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            withContext(Dispatchers.Main) {
                if (moviesList!=null && moviesList.results.isNotEmpty())
                    findViewById<TextView>(R.id.textView).text = moviesList.results[0].overview
            }
        }
    }
}
