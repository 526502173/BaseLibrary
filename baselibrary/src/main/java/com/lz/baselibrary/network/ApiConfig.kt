package com.lz.baselibrary.network

import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Api 配置属性
 * @author linzheng
 */
data class ApiConfig(
        val retrofitConfig: RetrofitConfig = RetrofitConfig(),
        val okHttpConfig: OkHttpConfig = OkHttpConfig()
)

/**
 * OkHttp 配置信息
 */
data class OkHttpConfig(
        val timeoutConnectMinutes: Long = 1L,
        val timeoutReadMinutes: Long = 1L,
        val timeoutWriteMinutes: Long = 1L,
        val interceptorList: List<Interceptor> = listOf(),
        val networkInterceptorList: List<Interceptor> = listOf(),
        val cache: Cache? = null,
        val httpsConfig: OkHttpHttpsConfig? = null
)

/**
 * OkHttp 的 https 配置
 */
data class OkHttpHttpsConfig(
        val cookieJar: CookieJar? = null,
        val sslSocketFactory: SSLSocketFactory? = null,
        val trustManager: X509TrustManager? = null
)

/**
 * Retrofit 配置信息
 */
data class RetrofitConfig(
        val baseUrl: String = "",
        val callAdapterFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()),
        val converterFactory: Converter.Factory = MoshiConverterFactory.create()
)