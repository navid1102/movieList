package com.navid.moviescore.core.splash

import com.navid.moviescore.apicalls.ResponseState
import com.navid.moviescore.core.splash.pojo.MovieListResponce


interface Splash

fun Splash.apiGetMovieList(
    page :Int,
    movieListResponce : (MovieListResponce) -> Unit
    , errorState: (String) -> Unit


) {
    com.navid.moviescore.core.BaseCore.apply {
        val url = com.navid.moviescore.BaseUri.baseUrl + "3/trending/movie/day?api_key=dad3a44acbc3a78931257cb7c343fd1d&page=$page"
        response.setCall(
            MovieListResponce::class.java, create.get(url)
        ) { responseState, data ->
            when (responseState) {
                ResponseState.OKAY -> {
                    data?.let {
                        movieListResponce(it)
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