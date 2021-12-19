package com.navid.moviescore.apicalls

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {
        fun <T> mapperTojson(tClass: Class<T>?, data: JsonObject?): T {

            val gson = GsonBuilder().serializeNulls().create()
            val gsonLog = GsonBuilder().setPrettyPrinting().create()
            Log.e("<<<<<<<<" + tClass?.simpleName + ">>>>>>>>", gsonLog.toJson(data))
            return gson.fromJson(gson.toJson(data), tClass)
        }

        fun <T> mapperTojson(tClass: Class<T>?, data: ResponseBody?): T {

            val gson = GsonBuilder().serializeNulls().create()
            val gsonLog = GsonBuilder().setPrettyPrinting().create()
            Log.e("<<<<<<<<" + tClass?.simpleName + ">>>>>>>>", gsonLog.toJson(data))
            return gson.fromJson(gson.toJson(data), tClass)
        }

    }
}