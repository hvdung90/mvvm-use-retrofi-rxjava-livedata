package com.example.myapplication.di.builder

import com.example.myapplication.view.MainFragment
import com.example.myapplication.view.OneFragment
import com.example.myapplication.view.ThreeFragment
import com.example.myapplication.view.TwoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Dunghv
 * khi them Frament muon dung Repository phai khai bao fragment
 */
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun oneFragment(): OneFragment

    @ContributesAndroidInjector
    abstract fun twoFragment(): TwoFragment

    @ContributesAndroidInjector
    abstract fun treeFragment(): ThreeFragment

}