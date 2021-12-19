package com.navid.movielist_mvvm.module

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class BaseModel:BaseObservable() {

    @Bindable
    var mainLoading: Boolean? = false
        set(value) {
            value.let {
                field = it
                notifyPropertyChanged(BR.mainLoading)
            }
        }



    fun showLoading() {
        mainLoading = true
    }

    fun stopLoading() {
        mainLoading = false

    }

}