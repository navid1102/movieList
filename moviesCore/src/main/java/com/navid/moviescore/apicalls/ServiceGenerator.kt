package com.navid.moviescore.apicalls

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.navid.moviescore.BaseUri
import com.navid.moviescore.customs.MyShared
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {


    private val httpClient = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    @SuppressLint("MissingPermission")
    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun <S> createService(serviceClass: Class<S>, ms: MyShared): S {
        if (retrofit == null) {

            val builder = Retrofit.Builder()
                .baseUrl(BaseUri.baseUrl)

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(logging)

            httpClient.connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)

            val client = httpClient.build()


            retrofit = builder.client(client).addConverterFactory(GsonConverterFactory.create()).build()


            return retrofit!!.create(serviceClass)


        } else {
            return retrofit!!.create(serviceClass)

        }
    }

}
