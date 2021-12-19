package com.navid.movielist_mvvm.ui.home.adapter

import android.content.Context
import com.navid.movielist_mvvm.R
import com.navid.movielist_mvvm.databinding.ItemListMovieBinding
import com.navid.movielist_mvvm.module.BaseAdapter
import com.navid.moviescore.BaseUri
import com.navid.moviescore.core.home.pojo.MovieResponse
import setImageUrl

class MovieListAdapter(
    private var listMovies: MutableList<MovieResponse?>? = null,
    var onclick: ((String) -> Unit)? = null
) : BaseAdapter<MovieResponse, ItemListMovieBinding>(listMovies) {

    fun onItemClicked(onclick: ((String) -> Unit)? = null) {
        this.onclick = onclick
    }


    override fun getLayoutResourceId(): Int {

        return R.layout.item_list_movie
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemListMovieBinding>, position: Int) {
        val context: Context = holder.itemView.context

        list.let {

            val movie = listMovies?.get(position)
            if (movie?.poster_path != null) {
                holder.binding.imgPoster.setImageUrl(BaseUri.TMDB_IMAGEURL + movie.poster_path)
            }
            holder.binding.textViewTitle.text = list?.get(position)?.title?:"".toString()
            holder.binding.txtType.text= list?.get(position)?.original_language?:"".toString()
            holder.binding.txtYear.text=list?.get(position)?.release_date?:"".toString()


            holder.itemView.setOnClickListener { _ ->


                onclick?.let { click ->
                    click(
                        list?.get(position)?.id.toString()
                    )
                }
            }


        }
    }

    fun addMovies(movieList: List<MovieResponse>) {
        if (movieList.size == 0) return
        val itemInsertedIndex = list?.size
        this.listMovies?.addAll(movieList)
        notifyItemInserted(itemInsertedIndex ?: 0)
    }

    fun clearMovies() {
        this.listMovies?.clear()
        notifyDataSetChanged()
    }


    fun updateData(data: List<MovieResponse?>?) {
        list = data
        notifyDataSetChanged()
    }
}