package com.core.lib.common.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by Dunghv
 */
object UnsafeOkHttpClient {
    fun getUnsafeOkHttpClient(isDevMode: Boolean): OkHttpClient {
        try {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            var okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(35, TimeUnit.SECONDS)
                    .addInterceptor(LoggingInterceptor())
            if (isDevMode) {
                okHttpClient.addInterceptor(logging)
            }
            try { // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(javax.security.cert.CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    @Throws(javax.security.cert.CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                })
                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts,
                        SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext
                        .socketFactory
                okHttpClient = okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//                if (isDevMode) {
//                    okHttpClient = okHttpClient.hostnameVerifier(HostnameVerifier { s: String?, sslSession: SSLSession? -> true })
//                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            return okHttpClient.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}