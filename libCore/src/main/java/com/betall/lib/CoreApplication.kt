package com.betall.lib

import android.app.Application
import com.lixi.libcore.utils.AppPreferenceUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class CoreApplication  : Application(), HasAndroidInjector  {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppPreferenceUtils.init(this)
    }

    //region Handle Oncreate application
    private fun setUpApplication() {
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: CoreApplication? = null
            private set
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}