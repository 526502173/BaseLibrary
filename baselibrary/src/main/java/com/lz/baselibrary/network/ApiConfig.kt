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
data class ApiConfig(val retrofitConfig: RetrofitConfig) {
    companion object {
        fun create(retrofitConfig: RetrofitConfig = RetrofitConfig.create()) = ApiConfig(retrofitConfig)
    }
}

/**
 * OkHttp 配置信息
 */
data class OkHttpConfig(
        val timeoutConfig: OkHttpTimeoutConfig,
        val interceptorList: List<Interceptor>,
        val networkInterceptorList: List<Interceptor>,
        val cache: Cache?,
        val httpsConfig: OkHttpHttpsConfig?
) {
    companion object {
        fun create(
                timeoutConfig: OkHttpTimeoutConfig = OkHttpTimeoutConfig.create(),
                interceptorList: List<Interceptor> = listOf(),
                networkInterceptorList: List<Interceptor> = listOf(),
                cache: Cache? = null,
                httpsConfig: OkHttpHttpsConfig? = null
        ) = OkHttpConfig(timeoutConfig, interceptorList, networkInterceptorList, cache, httpsConfig)
    }
}

/**
 * okhttp 超时相关配置
 */
data class OkHttpTimeoutConfig(
        val timeoutConnectMinutes: Long = 1L,
        val timeoutReadMinutes: Long = 1L,
        val timeoutWriteMinutes: Long = 1L
) {
    companion object {
        fun create(
                timeoutConnectMinutes: Long = 1L,
                timeoutReadMinutes: Long = 1L,
                timeoutWriteMinutes: Long = 1L
        ) = OkHttpTimeoutConfig(timeoutConnectMinutes, timeoutReadMinutes, timeoutWriteMinutes)
    }
}

/**
 * OkHttp 的 https 配置
 */
data class OkHttpHttpsConfig(
        val cookieJar: CookieJar?,
        val sslSocketFactory: SSLSocketFactory?,
        val trustManager: X509TrustManager?
) {
    companion object {
        fun create(
                cookieJar: CookieJar? = null,
                sslSocketFactory: SSLSocketFactory? = null,
                trustManager: X509TrustManager? = null
        ) = OkHttpHttpsConfig(cookieJar, sslSocketFactory, trustManager)
    }
}

/**
 * Retrofit 配置信息
 */
data class RetrofitConfig(
        val baseUrl: String,
        val clientConfig: OkHttpConfig,
        val callAdapterFactory: CallAdapter.Factory,
        val converterFactory: Converter.Factory
) {
    companion object {
        fun create(
                baseUrl: String = "",
                clientConfig: OkHttpConfig = OkHttpConfig.create(),
                callAdapterFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()),
                converterFactory: Converter.Factory = MoshiConverterFactory.create()
        ) = RetrofitConfig(baseUrl, clientConfig, callAdapterFactory, converterFactory)
    }
}