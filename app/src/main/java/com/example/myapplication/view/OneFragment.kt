package com.example.myapplication.view

import android.annotation.SuppressLint
import androidx.navigation.Navigation
import com.betall.lib.common.base.ui.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.OneMainBinding
import com.example.myapplication.viewModel.MainViewModel
import com.jakewharton.rxbinding2.view.RxView

class OneFragment : BaseFragment<MainViewModel, OneMainBinding>() {

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override val layoutRes: Int
        get() = R.layout.one_main

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