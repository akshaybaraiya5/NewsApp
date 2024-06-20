package com.example.newsapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.newsapplication.model.Articles


@Dao
interface NewsDao {

    @Insert
    suspend fun addArticles(quotes: List<Articles>)

    @Update
    suspend fun updateArticle(article: List<Articles>)

    @Query("SELECT * FROM articles")
    suspend fun getArticles() : List<Articles>
}