package com.navid.movielist_mvvm.module

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.navid.moviescore.customs.MyShared
import org.koin.android.ext.android.inject


abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B
    val ms: MyShared by inject()


    abstract fun getViewRes(): Int
    abstract fun oncreat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getViewRes())


        oncreat()

        supportActionBar?.hide()
    }


    protected fun addFragment(fragment: Fragment, view: View) {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(view.id, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }


}