package com.navid.moviescore.customs

import android.content.Context
import android.content.SharedPreferences

open class MyShared(var context: Context) {


    open fun setShared(key: String, value: Any?) {
        val userInfo: SharedPreferences =
            context.getSharedPreferences("NF", Context.MODE_PRIVATE)
        val edit = userInfo.edit()
        when (value) {
            is String? -> edit.putString(key, value)
            else -> throw UnsupportedOperationException("Not yet implemented") as Throwable
        }
        edit.apply()
    }

    open fun getShared(): SharedPreferences {

        return context.getSharedPreferences("NF", 0)

    }

    fun clean() {

        apply {
            moview_list=null
        }
    }


    var moview_list:String?
    get() = getShared().getString("movie_list",null)
    set(value) = setShared("movie_list",value)

}
