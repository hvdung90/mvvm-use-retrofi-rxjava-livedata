package com.example.myapplication.view

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.core.lib.common.base.ui.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.TwoMainBinding
import com.example.myapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TwoFragment : BaseFragment<MainViewModel, TwoMainBinding>() {
    override val mViewModel: MainViewModel by viewModels()
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