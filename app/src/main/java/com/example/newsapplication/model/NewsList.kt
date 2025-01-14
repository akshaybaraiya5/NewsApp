package com.example.newsapplication.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class NewsList (
  @SerializedName("status"       ) var status       : String?             = null,
  @SerializedName("totalResults" ) var totalResults : Int?                = null,
  @SerializedName("articles"     ) var articles     : ArrayList<Articles> = arrayListOf()

)