package com.example.newsapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapplication.api.ApiService
import com.example.newsapplication.db.NewsDatabase
import com.example.newsapplication.model.Articles
import com.example.newsapplication.model.NewsList
import com.example.newsapplication.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository(
    private val newsService: ApiService,
    private val newsDatabase: NewsDatabase,
    private val applicationContext: Context
) {


    private val newLiveData = MutableLiveData<List<Articles>>()
    val userList: LiveData<List<Articles>>
        get() = newLiveData
    private val api = newsService
    var newsList: List<Articles> = listOf()

    suspend fun getNews(country: String, category: String) {
        val result = api.getNews(country, category, "9629737bc3bd4824af91243d66df7c36")
        val news = newsDatabase.newsDao().getArticles()




        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            result.enqueue(object : Callback<NewsList?> {
                override fun onResponse(call: Call<NewsList?>, response: Response<NewsList?>) {
                    val dataList = response.body()
                    newsList = dataList!!.articles!!
                    newLiveData.postValue(newsList)
                    Log.d("data", "articles created")
                }

                override fun onFailure(call: Call<NewsList?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })




        } else {
            newLiveData.postValue(news)
        }



    }







}