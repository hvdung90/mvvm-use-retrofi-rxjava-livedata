package com.example.myapplication.di.module

import com.betall.lib.common.retrofit.CoreiFactory
import com.betall.lib.common.retrofit.UnsafeOkHttpClient
import com.example.myapplication.BuildConfig
import com.example.myapplication.apiService.MainApiService

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Dunghv
 * khi them ApiService phai them vao
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return UnsafeOkHttpClient.getUnsafeOkHttpClient(BuildConfig.DEBUG)
    }

    @Provides
    @Singleton
    fun provideHomeApiService(okHttpClient: OkHttpClient): MainApiService {
        return CoreiFactory.create(okHttpClient, MainApiService::class.java, BuildConfig.BASE_URL)
    }

}