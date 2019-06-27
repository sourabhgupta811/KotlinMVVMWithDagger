package com.example.repository.network.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

private const val tableName = "movie_list"

@Entity(tableName = tableName)
data class MoviesList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    companion object{
        fun getTableName():String{
            return tableName
        }
    }
}