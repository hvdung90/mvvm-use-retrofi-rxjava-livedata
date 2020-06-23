package com.betall.lib.common.retrofit

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Dunghv
 */
class MockInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        response = chain.proceed(chain.request())
        Log.d(TAG, "---- Response: $response")
        return response
    }

    companion object {
        private val TAG = MockInterceptor::class.java.name
    }

}