package com.example.newsapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.adapter.CategoryAdapter
import com.example.newsapplication.adapter.NewsAdapter

import com.example.newsapplication.api.ApiService
import com.example.newsapplication.api.RetrofitHelper
import com.example.newsapplication.db.NewsDatabase
import com.example.newsapplication.factory.NewsViewModelFactory
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.view_model.NewViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var newsRepository: NewsRepository
    private  lateinit var newsViewModel: NewViewModel
    private  lateinit var newsViewModelFactory: NewsViewModelFactory
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var category: List<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val database = NewsDatabase.getDatabase(applicationContext)
        newsRepository = NewsRepository(newsService,database,applicationContext)

        category = listOf("business","entertainment","general","health","science","sports","technology")
        adapter = NewsAdapter(this)
       categoryAdapter = CategoryAdapter(this,category) {
           getNews()
       }



        recyclerView = findViewById(R.id.newsRecyclerView)
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)
        progressBar = findViewById(R.id.progressBar2)



        categoryRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, true,)
        if(resources.configuration.orientation==android.content.res.Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(this,2)

        } else {
            recyclerView.layoutManager = LinearLayoutManager(this,)
        }
        categoryRecyclerView.adapter = categoryAdapter
        recyclerView.adapter = adapter
        newsViewModelFactory =  NewsViewModelFactory(newsRepository,"in",categoryAdapter.categoryCurrent)
        newsViewModel = ViewModelProvider(this,newsViewModelFactory).get(
            NewViewModel::class.java)
        getNews()


    }


    private fun getNews(){
        newsViewModel.getNews(categoryAdapter.categoryCurrent)
        newsViewModel.news.observe(this) { news ->
            adapter.setUsers(news)
            Log.d("data",news.toString())
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }


    }
}