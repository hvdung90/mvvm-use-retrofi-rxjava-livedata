package com.core.lib.common.retrofit

import com.core.lib.BuildConfig
import com.core.lib.CoreApplication
import com.core.lib.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * Created by Dunghv
 */
class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val accessToken = "abc"
        if (accessToken != null && accessToken.isNotEmpty()) {
            builder.addHeader("token", accessToken)
        }
        builder.addHeader("lang", lang)
        builder.addHeader("Content-Type", "application/json")
        builder.addHeader("platform", "android")
        builder.addHeader("version", "1.0.0")//if (BuildConfig.DEBUG) "1.0.0" else BuildConfig.VERSION_NAME)
        if (!NetworkUtils.isConnected(CoreApplication.instance!!))
            throw NoConnectivityException()
        else
            return chain.proceed(builder.build())
    }

    inner class NoConnectivityException : IOException() {
        override val message: String
            get() = "No network available, please check your WiFi or Data connection"
    }

    companion object {
        var lang = "vi"
    }
}