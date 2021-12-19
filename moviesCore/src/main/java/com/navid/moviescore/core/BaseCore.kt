package com.navid.moviescore.core

import android.content.res.Resources
import com.navid.moviescore.apicalls.ApiInterface
import com.navid.moviescore.apicalls.ApiResponce
import com.navid.moviescore.customs.MyShared
import org.koin.core.KoinComponent
import org.koin.core.inject

object BaseCore : KoinComponent {
    val ms: MyShared by inject()
    val response: ApiResponce by inject()
    val create: ApiInterface by inject()
    val res: Resources by inject()
}