package com.navid.moviescore.core.home.detail

import com.navid.moviescore.apicalls.ResponseState
import com.navid.moviescore.core.home.detail.pojo.DetailResponse

interface Detail

fun Detail.apiGetDetail(
    movieId:String,
    detailResponse: (DetailResponse) -> Unit
    , errorState: (String) -> Unit


) {
    com.navid.moviescore.core.BaseCore.apply {
        val url = com.navid.moviescore.BaseUri.baseUrl +"3/movie/$movieId?api_key=dad3a44acbc3a78931257cb7c343fd1d"
        response.setCall(
            DetailResponse::class.java, create.get(url)
        ) { responseState, data ->
            when (responseState) {
                ResponseState.OKAY -> {
                    data?.let {
                        detailResponse(it)
                        android.util.Log.d(
                            "navid",
                            com.google.gson.GsonBuilder()
                                .setPrettyPrinting().create().toJson(kotlin.Any())
                        )
                    }
                }
                ResponseState.InternetFailed -> {
                    android.util.Log.e("movieList", "InternetFailed map")
                    errorState(res.getString(com.navid.moviescore.R.string.internetError))
                }

                ResponseState.ResponceNot200 -> {
                    errorState(res.getString(com.navid.moviescore.R.string.responceNot200))

                }
                ResponseState.ServerError -> {
                    android.util.Log.e("movieList", "ServerError map")
                    errorState(res.getString(com.navid.moviescore.R.string.serverError))
                }

            }
        }
    }

}