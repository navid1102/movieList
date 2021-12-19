package com.navid.moviescore.core.splash.pojo

data class MovieListResponce(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)