package com.navid.moviescore.di

import android.content.Context
import com.navid.moviescore.apicalls.ApiInterface
import com.navid.moviescore.apicalls.ApiResponce
import com.navid.moviescore.apicalls.ServiceGenerator
import com.navid.moviescore.customs.MyShared
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sdkModule = module {
    single { MyShared(androidContext()) }
    single { ApiResponce() }
    single { apiInterface(get()) }
    single { androidContext().resources }
}

fun apiInterface(context: Context): ApiInterface {
    return ServiceGenerator.createService(ApiInterface::class.java, MyShared(context))
}