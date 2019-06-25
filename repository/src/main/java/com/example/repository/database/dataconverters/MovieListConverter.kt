package com.example.repository.database.dataconverters

/**
 * Created by Sourabh Gupta on 25/6/19.
 */

import androidx.room.TypeConverter
import com.example.repository.network.models.MoviesList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieListConverter {

    @TypeConverter
    fun fromMovieList(value: List<MoviesList>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MoviesList>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMovieList(value: String): List<MoviesList> {
        val gson = Gson()
        val type = object : TypeToken<List<MoviesList>>() {}.type
        return gson.fromJson(value, type)
    }
}