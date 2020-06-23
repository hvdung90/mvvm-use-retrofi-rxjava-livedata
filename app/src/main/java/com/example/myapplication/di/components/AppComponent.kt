package com.example.myapplication.di.components

import android.app.Application
import com.example.myapplication.BetallApplication
import com.example.myapplication.di.builder.ActivityBuilderModule
import com.example.myapplication.di.builder.FragmentBuilderModule
import com.example.myapplication.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class, FragmentBuilderModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(lixiApplication: BetallApplication)
}