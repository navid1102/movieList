package com.navid.movielist_mvvm.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.navid.moviescore.core.home.pojo.Genre

class SpinnerAdapter(
    private val context: Context,
    private val genres: MutableList<Genre>
) : BaseAdapter() {

    init {
        genres.add(0, Genre(null, "All"))
    }

    override fun getCount(): Int = genres.size

    override fun getItem(position: Int) = genres[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val textView = LayoutInflater.from(context)
                .inflate(android.R.layout.simple_spinner_item, null) as TextView
            textView.text = getItem(position).name
            return textView
        }
        (convertView as TextView).text = getItem(position).name
        return convertView
    }

}