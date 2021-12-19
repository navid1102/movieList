package com.navid.movielist_mvvm

import androidx.multidex.MultiDexApplication
import com.navid.moviescore.Server
import com.navid.moviescore.UrlType

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        Server.setServerType(UrlType.Public)


    }
}