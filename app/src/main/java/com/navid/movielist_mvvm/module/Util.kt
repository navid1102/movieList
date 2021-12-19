package com.navid.movielist_mvvm.module

import android.content.Context
import android.net.ConnectivityManager


class Util {
    companion object {
        fun isOnline(context: Context): Boolean {
            return try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = cm.activeNetworkInfo
                //should check null because in airplane mode it will be null
                netInfo != null && netInfo.isConnected
            } catch (e: NullPointerException) {
                e.printStackTrace()
                false
            }
        }

    }

}


