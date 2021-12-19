package com.navid.moviescore.apicalls

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    fun get(@Url url: String): retrofit2.Call<JsonObject>

}