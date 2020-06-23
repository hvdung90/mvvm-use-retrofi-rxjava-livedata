package com.example.myapplication

import com.betall.lib.CoreApplication
import com.example.myapplication.di.components.DaggerAppComponent

class BetallApplication :CoreApplication(){
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}