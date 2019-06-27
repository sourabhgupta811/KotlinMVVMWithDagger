package com.example.repository.network.api

import android.util.Log
import com.example.repository.network.constants.NetworkConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.repository.network.models.MoviesList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi{
    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("sort_by") sortBy:String,
        @Query("page") pageNumber:Int
    ): MoviesList


    companion object{
        operator fun invoke(): TMDBApi {
            val interceptor = Interceptor { chain->
                val requestUrl = chain.request().url().newBuilder().addQueryParameter("api_key",
                    NetworkConstants.TMDB_API_KEY).build()
                val request  = Request.Builder().url(requestUrl).build()
                val response = chain.proceed(request)
                return@Interceptor response

            }
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val tMDBApi = Retrofit.Builder()
                .baseUrl(NetworkConstants.TMDB_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBApi::class.java)
            return tMDBApi
        }
    }
}