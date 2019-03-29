package com.lz.baselibrary.network

import com.lz.baselibrary.ApiConfigNotInitException
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * @author linzheng
 */
object Api {

    /**
     * Api 的配置信息对象
     */
    lateinit var mApiConfig: ApiConfig

    /**
     * OkHttp 对象
     */
    private val mOkHttpClient by lazy(LazyThreadSafetyMode.NONE) {
        OkHttpClient.Builder().run {
            mApiConfig.okHttpConfig.apply {
                interceptorList.forEach { addInterceptor(it) }
                networkInterceptorList.forEach { addNetworkInterceptor(it) }
                connectTimeout(timeoutConnectMinutes, TimeUnit.MINUTES)
                readTimeout(timeoutReadMinutes, TimeUnit.MINUTES)
                writeTimeout(timeoutWriteMinutes, TimeUnit.MINUTES)
                if (cache != null) cache(cache)
                if (cookieJar != null) cookieJar(cookieJar)
                if (sslSocketFactory != null && trustManager != null)
                    sslSocketFactory(sslSocketFactory, trustManager)
            }
            build()
        }
    }

    /**
     * Retrofit 对象
     */
    private val mRetrofit by lazy(LazyThreadSafetyMode.NONE) {
        if(!this::mApiConfig.isInitialized)
            throw ApiConfigNotInitException()
        mApiConfig.retrofitConfig.run {
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(mOkHttpClient)
                    .validateEagerly(true)
                    .addCallAdapterFactory(callAdapterFactory)
                    .addConverterFactory(converterFactory)
                    .build()
        }
    }

    /**
     * 将 Retrofit 中的 create() 转换到 Kotlin 中
     */
    fun <T : Any> createApi(clazz: KClass<out T>) = mRetrofit.create(clazz.java)!!

}
