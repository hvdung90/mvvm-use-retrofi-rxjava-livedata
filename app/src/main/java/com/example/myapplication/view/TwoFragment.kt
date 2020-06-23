package com.example.myapplication.view

import android.annotation.SuppressLint
import androidx.navigation.Navigation
import com.betall.lib.common.base.ui.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.databinding.TwoMainBinding
import com.example.myapplication.viewModel.MainViewModel
import com.jakewharton.rxbinding2.view.RxView

class TwoFragment :BaseFragment<MainViewModel,TwoMainBinding>(){

    override fun getViewModel(): Class<MainViewModel> {
        return  MainViewModel::class.java
    }

    override val layoutRes: Int
        get() = R.layout.two_main

    override fun init() {
        initLayout()
        initData()
        initBehavior()
    }

    @SuppressLint("CheckResult")
    override fun initLayout() {
        binding.txt.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_next))
    }

    override fun initData() {

    }

    override fun initBehavior() {

    }
}