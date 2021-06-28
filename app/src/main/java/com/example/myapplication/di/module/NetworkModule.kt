package com.example.myapplication.di.module

import com.core.lib.common.retrofit.CoreFactory
import com.core.lib.common.retrofit.UnsafeOkHttpClient
import com.example.myapplication.BuildConfig
import com.example.myapplication.apiService.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Dunghv
 * khi them ApiService phai them vao
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return UnsafeOkHttpClient.getUnsafeOkHttpClient(BuildConfig.DEBUG)
    }

    @Singleton
    @Provides
    fun provideHomeApiService(okHttpClient: OkHttpClient): MainApiService {
        return CoreFactory.create(okHttpClient, MainApiService::class.java, BuildConfig.BASE_URL)
    }

}