package com.navid.movielist_mvvm.ui.home.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.navid.moviescore.core.home.pojo.MovieResponse


class AutoSuggestAdapter(context: Context, resource: Int) :
    ArrayAdapter<String>(context, resource) {

    private val movieList: MutableList<MovieResponse>

    init {
        movieList = ArrayList()
    }

    fun setData(list: List<MovieResponse>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): String {
        return movieList[position].original_title ?: ""
    }


    override fun getItemId(position: Int): Long {
        return movieList[position].id?.toLong() ?: 0
    }

}