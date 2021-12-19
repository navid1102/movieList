package com.navid.movielist_mvvm.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.navid.movielist_mvvm.module.BaseViewModel
import com.navid.moviescore.core.home.Home
import com.navid.moviescore.core.home.apiGetGenerList
import com.navid.moviescore.core.home.apiGetMovieList
import com.navid.moviescore.core.home.apiSearch
import com.navid.moviescore.core.home.pojo.GenerResponse
import com.navid.moviescore.core.home.pojo.MoviesResponse
import com.navid.moviescore.core.splash.pojo.MovieListResponce

class HomeViewModel : BaseViewModel(), Home {

    val liveMovieList by lazy { MutableLiveData<MoviesResponse>() }
    val liveGenerList by lazy { MutableLiveData<GenerResponse>() }
    val liveSearch by lazy { MutableLiveData<MoviesResponse>() }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getMovieList(page:Int,gener:String){
        baseModel.showLoading()
        apiGetMovieList(page,gener,{
            baseModel.stopLoading()
            liveMovieList.value=it
        },errorState = {})
    }


    fun getGenerList(){
        apiGetGenerList({
            liveGenerList.value=it
        },errorState = {
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getSearch(query:String){
        apiSearch(query,{
            liveSearch.value=it
        },errorState = {})
    }

}