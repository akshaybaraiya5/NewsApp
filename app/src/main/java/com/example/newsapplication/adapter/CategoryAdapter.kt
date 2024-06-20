package com.example.newsapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R

class CategoryAdapter(private val context: Context, private val categoryName:List<String>, private val onCategoryClicked:()->Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
     var categoryCurrent: String = "science"
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryButton:Button = itemView.findViewById(R.id.categoryButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  categoryName.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var category = categoryName[position]
        holder.categoryButton.text = category
        holder.categoryButton.setOnClickListener {
            categoryCurrent = category
            onCategoryClicked()
        }

    }
}