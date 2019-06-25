package com.example.repository.database.db

import android.content.Context

/**
 * Created by Sourabh Gupta on 25/6/19.
 */
object DatabaseKeeper {
    fun getDatabase(context: Context): TMDBDatabase {
        return TMDBDatabase(context)
    }
}