package com.sourabh.xplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.repository.network.models.MoviesList
import com.example.repository.repo.MovieRepository
import com.sourabh.xplayer.viewmodel.MainActivityViewModel
import com.sourabh.xplayer.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var movieLIstLiveData: LiveData<List<MoviesList>>
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel =
            ViewModelProviders.of(this, MainActivityViewModelFactory()).get(MainActivityViewModel::class.java)
        movieLIstLiveData = mainActivityViewModel.movieListLiveData
        movieLIstLiveData.observe(this, Observer {
            for (item in it) {
                textView.append(item.toString())
                textView.append("\n\n*******************************************************************************************************************************************************************************\n")
            }
        })
        mainActivityViewModel.getMoviesList(applicationContext)
    }
}
