package com.betall.lib.common.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dunghv
 */
object CoreiFactory {
    fun <T> create(okHttpClient: OkHttpClient?, serviceClass: Class<T>?, baseUrl: String?): T {
        try {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            return retrofit.build().create(serviceClass)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}