package com.navid.moviescore.core.home.pojo

data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)