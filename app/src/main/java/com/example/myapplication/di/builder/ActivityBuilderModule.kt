package com.example.myapplication.di.builder

import com.example.myapplication.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Dunghv
 * khi them Activity muon dung Repository phai khai bao Activity
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun homeActivity(): MainActivity?
}