package com.example.repository.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repository.database.dao.MovieListDao
import com.example.repository.database.dataconverters.IntListConverter
import com.example.repository.database.dataconverters.MovieListConverter
import com.example.repository.database.dataconverters.ResultConverter
import com.example.repository.network.models.MoviesList
import com.example.repository.network.models.Result

/**
 * Created by Sourabh Gupta on 25/6/19.
 */
const val DATABASE_NAME = "movie_database"
@TypeConverters(ResultConverter::class,MovieListConverter::class,IntListConverter::class)
@Database(entities = [MoviesList::class, Result::class], version = 1)
abstract class TMDBDatabase : RoomDatabase() {
    //Dao
    abstract fun movieListDao(): MovieListDao

    companion object{
        var instance: TMDBDatabase? = null
        operator fun invoke(context:Context): TMDBDatabase {
            if(instance ==null) {
                instance = Room.databaseBuilder(context.applicationContext, TMDBDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
            }
            return instance as TMDBDatabase
        }
    }
}