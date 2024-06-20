package com.example.newsapplication.api

import com.example.newsapplication.model.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<NewsList>
}