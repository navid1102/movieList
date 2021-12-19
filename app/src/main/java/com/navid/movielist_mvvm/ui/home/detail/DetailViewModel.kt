package com.navid.movielist_mvvm.ui.home.detail

import androidx.lifecycle.MutableLiveData
import com.navid.movielist_mvvm.module.BaseViewModel
import com.navid.moviescore.core.home.detail.Detail
import com.navid.moviescore.core.home.detail.apiGetDetail
import com.navid.moviescore.core.home.detail.pojo.DetailResponse

class DetailViewModel:BaseViewModel(),Detail {

    val liveDetailMovie by lazy { MutableLiveData<DetailResponse>() }


    fun getDetailMovie(moviId:String){
        baseModel.showLoading()
        apiGetDetail(moviId,{
            baseModel.stopLoading()
            liveDetailMovie.value=it
        },errorState = {
            baseModel.stopLoading()
        })
    }

}