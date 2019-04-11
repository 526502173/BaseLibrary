package com.lz.baselibrary.network

import com.lz.baselibrary.ApiConfigNotInitException
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

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
    private val sOkHttpClient by lazy {
        OkHttpClient.Builder().run {
            sApiConfig.okHttpConfig.apply {
                interceptorList.forEach { addInterceptor(it) }
                networkInterceptorList.forEach { addNetworkInterceptor(it) }
                connectTimeout(timeoutConnectMinutes, TimeUnit.MINUTES)
                readTimeout(timeoutReadMinutes, TimeUnit.MINUTES)
                writeTimeout(timeoutWriteMinutes, TimeUnit.MINUTES)
                if (cache != null) cache(cache)
                if (httpsConfig?.cookieJar != null) cookieJar(httpsConfig.cookieJar)
                if (httpsConfig?.sslSocketFactory != null && httpsConfig.trustManager != null)
                    sslSocketFactory(httpsConfig.sslSocketFactory, httpsConfig.trustManager)
            }
            build()
        }
    }

    /**
     * Retrofit 对象
     */
    private val sRetrofit by lazy { buildRetrofit() }

    /**
     * 同步 Retrofit 对象，用于同步执行请求
     */
    private val sSyncRetrofit by lazy { buildRetrofit(true) }

    //todo 需要考虑自定义的 CallAdapter 这里假设 CallAdapter 的实现只有 RxJavaCallAdapter
    private fun buildRetrofit(isSync: Boolean = false) = if (!this::sApiConfig.isInitialized)
        throw ApiConfigNotInitException()
    else sApiConfig.retrofitConfig.run {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(sOkHttpClient)
                .validateEagerly(true)
                .addCallAdapterFactory(if (isSync) RxJava2CallAdapterFactory.create() else callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
    }

    /**
     * 将 Retrofit 中的 create() 转换到 Kotlin 中
     */
    fun <T : Any> createApi(clazz: KClass<out T>) = sRetrofit.create(clazz.java)!!

    fun <T : Any> createSyncApi(clazz: KClass<out T>) = sSyncRetrofit.create(clazz.java)!!

}
