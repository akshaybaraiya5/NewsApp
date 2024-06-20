package com.example.newsapplication.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.example.newsapplication.model.Source

class Converters {
   private val gson = Gson()

   @TypeConverter
   fun fromSource(source: Source?): String? {
      return gson.toJson(source)
   }

   @TypeConverter
   fun toSource(sourceString: String?): Source? {
      return gson.fromJson(sourceString, Source::class.java)
   }
}
