package com.lz.baselibrary.network

import com.lz.baselibrary.ApiConfigNotInitException
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 的封装类
 * @author linzheng
 */
object Api {

    /**
     * Api 的配置信息对象
     */
    lateinit var sApiConfig: ApiConfig

    /**
     * OkHttp 对象
     */
    private val OKHTTP_CLIENT by lazy {
        OkHttpClient.Builder().run {
            sApiConfig.retrofitConfig.clientConfig.apply {
                interceptorList.forEach { addInterceptor(it) }
                networkInterceptorList.forEach { addNetworkInterceptor(it) }
                connectTimeout(timeoutConfig.timeoutConnectMinutes, TimeUnit.MINUTES)
                readTimeout(timeoutConfig.timeoutReadMinutes, TimeUnit.MINUTES)
                writeTimeout(timeoutConfig.timeoutWriteMinutes, TimeUnit.MINUTES)
                if (cache != null) cache(cache)
                if (httpsConfig?.cookieJar != null) cookieJar(httpsConfig.cookieJar)
                if (httpsConfig?.sslSocketFactory != null && httpsConfig.trustManager != null)
                    sslSocketFactory(httpsConfig.sslSocketFactory, httpsConfig.trustManager)
            }
            build()
        }
    }

    /**
     * 异步 Retrofit 对象，用于异步执行请求
     */
    val ASYNC_RETROFIT: Retrofit by lazy { buildRetrofit() }

    /**
     * 同步 Retrofit 对象，用于同步执行请求
     */
    val SYNC_RETROFIT: Retrofit by lazy { buildRetrofit(true) }

    /**
     * 构建 Retrofit 对象
     * [isSync] true 表示构建的 Retrofit 对象是同步的，false 表示是异步的，默认使用 RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
     */
    private fun buildRetrofit(isSync: Boolean = false) = if (!this::sApiConfig.isInitialized)
        throw ApiConfigNotInitException()
    else sApiConfig.retrofitConfig.run {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OKHTTP_CLIENT)
                .validateEagerly(true)
                .addCallAdapterFactory(if (isSync) RxJava2CallAdapterFactory.create() else callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
    }
}
