package com.navid.movielist_mvvm.ui

import com.navid.movielist_mvvm.R
import com.navid.movielist_mvvm.databinding.ActivityMainBinding
import com.navid.movielist_mvvm.module.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun getViewRes(): Int {
        return R.layout.activity_main

    }

    override fun oncreat() {

    }

}