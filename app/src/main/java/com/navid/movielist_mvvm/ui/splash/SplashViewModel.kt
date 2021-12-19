package com.navid.movielist_mvvm.ui.splash

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.navid.movielist_mvvm.module.BaseViewModel
import com.navid.moviescore.core.splash.Splash
import com.navid.moviescore.core.splash.apiGetMovieList
import com.navid.moviescore.core.splash.pojo.MovieListResponce


class SplashViewModel: BaseViewModel(), Splash {

    val liveMovieList by lazy { MutableLiveData<MovieListResponce>() }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getMovieList(page:Int){
        apiGetMovieList(page,{
            liveMovieList.value=it
        },errorState = {})
    }

}