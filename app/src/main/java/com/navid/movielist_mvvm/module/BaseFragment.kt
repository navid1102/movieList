package com.navid.movielist_mvvm.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    lateinit var binding: B

    abstract fun getViewRes(): Int
    abstract fun oncreate()
    private var vieww: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (vieww == null) {
            binding = DataBindingUtil.inflate(inflater, getViewRes(), container, false)
            vieww = binding.root

            oncreate()
        }

        return vieww
    }

}