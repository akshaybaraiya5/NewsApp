package com.example.newsapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.view.NewsInfo
import com.example.newsapplication.R
import com.example.newsapplication.model.Articles
import com.squareup.picasso.Picasso


class NewsAdapter(private val context: Context):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

     private var newsItems:List<Articles> = listOf()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.news_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return newsItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsTitle.text = newsItems[position].title
        holder.newsInfo.text = newsItems[position].author
        Picasso.get().load(newsItems[position].urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {


            val intent = Intent(context, NewsInfo::class.java)
            intent.putExtra("url",newsItems[position].url)
            context.startActivity(intent)

        }
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       val newsImage:ImageView = itemView.findViewById(R.id.NewsImage)
       val newsTitle:TextView = itemView.findViewById(R.id.NewsHeadline)
       val newsInfo:TextView = itemView.findViewById(R.id.NewsInfo)


    }

    fun setUsers(users: List<Articles>) {
        this.newsItems = users
        notifyDataSetChanged()
    }
}