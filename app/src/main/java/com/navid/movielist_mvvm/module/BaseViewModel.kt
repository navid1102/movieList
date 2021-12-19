package com.navid.movielist_mvvm.module

import android.content.res.Resources
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.navid.moviescore.customs.MyShared
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent, LifecycleObserver {
    val ms: MyShared by inject()
    val res: Resources by inject()
    val baseModel = BaseModel()

}