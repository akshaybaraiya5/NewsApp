package com.example.newsapplication.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.Articles
import com.example.newsapplication.model.NewsList
import com.example.newsapplication.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class NewViewModel(
    private val newsRepository: NewsRepository,
    private val country: String,
    private var category: String,
) : ViewModel() {

    init {
        getNews(this.category)
    }

    fun getNews(category:String){
        Log.d("data","mode cate $category")
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNews(country,category)

        }
    }

    val news: LiveData<List<Articles>>
        get() = newsRepository.userList

}