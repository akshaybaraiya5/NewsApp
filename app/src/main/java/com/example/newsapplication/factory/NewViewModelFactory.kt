package com.example.newsapplication.factory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.view_model.NewViewModel

class NewsViewModelFactory(private val newsRepository: NewsRepository, private val country: String, private var category: String) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewViewModel(newsRepository,country,category) as T
    }
}