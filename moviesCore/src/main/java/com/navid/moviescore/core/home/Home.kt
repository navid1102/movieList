package com.navid.moviescore.core.home

import com.navid.moviescore.apicalls.ResponseState
import com.navid.moviescore.core.BaseCore
import com.navid.moviescore.core.BaseCore.response
import com.navid.moviescore.core.home.pojo.GenerResponse
import com.navid.moviescore.core.home.pojo.MoviesResponse
import com.navid.moviescore.core.splash.pojo.MovieListResponce

interface Home


fun Home.apiSearch(
    query: String,
    movieListResponce: (MoviesResponse) -> Unit
    , errorState: (String) -> Unit


) {
    com.navid.moviescore.core.BaseCore.apply {
        val url =
            com.navid.moviescore.BaseUri.baseUrl + "3/search/movie?api_key=dad3a44acbc3a78931257cb7c343fd1d&query=$query"
        response.setCall(
            MoviesResponse::class.java, create.get(url)
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

fun Home.apiGetGenerList(
    generResponse: (GenerResponse) -> Unit
    , errorState: (String) -> Unit
) {
    val url =
        com.navid.moviescore.BaseUri.baseUrl + "3/genre/movie/list?api_key=dad3a44acbc3a78931257cb7c343fd1d"
    response.setCall(
        GenerResponse::class.java, BaseCore.create.get(url)
    ) { responseState, data ->
        when (responseState) {
            ResponseState.OKAY -> {
                data?.let {
                    generResponse(it)
                    android.util.Log.d(
                        "navid",
                        com.google.gson.GsonBuilder()
                            .setPrettyPrinting().create().toJson(kotlin.Any())
                    )
                }
            }
            ResponseState.InternetFailed -> {
                android.util.Log.e("generList", "InternetFailed map")
                errorState(BaseCore.res.getString(com.navid.moviescore.R.string.internetError))
            }
            ResponseState.ResponceNot200 -> {
                errorState(BaseCore.res.getString(com.navid.moviescore.R.string.responceNot200))

            }
            ResponseState.ServerError -> {
                android.util.Log.e("generList", "ServerError map")
                errorState(BaseCore.res.getString(com.navid.moviescore.R.string.serverError))
            }
        }
    }


}

fun Home.apiGetMovieList(
    page: Int,
    geners: String,
    movieListResponce: (MoviesResponse) -> Unit
    , errorState: (String) -> Unit


) {
    com.navid.moviescore.core.BaseCore.apply {
        val url =
            com.navid.moviescore.BaseUri.baseUrl + "3/discover/movie?api_key=dad3a44acbc3a78931257cb7c343fd1d&page=1&with_genres=$geners"
        response.setCall(
            MoviesResponse::class.java, create.get(url)
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
