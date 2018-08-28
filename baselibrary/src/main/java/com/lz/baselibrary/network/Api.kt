package com.lz.baselibrary.network

import com.lz.baselibrary.LibraryApplication
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * @author linzheng
 */
object Api {

    var BASE_URL = ""

    private val mOkHttpClient by lazy(LazyThreadSafetyMode.NONE) {
        val interceptorList = LibraryApplication.getInstance().buildInterceptor()
        val networkInterceptor = LibraryApplication.getInstance().buildNetworkInterceptor()
        val cache = LibraryApplication.getInstance().buildCache()
        OkHttpClient.Builder().run {
            interceptorList.forEach {
                addInterceptor(it)
            }
            networkInterceptor.forEach {
                addNetworkInterceptor(it)
            }
            LibraryApplication.getInstance().buildOkHttpSSLSocketFactory(this)
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            cache(cache)
            cookieJar(LibraryApplication.getInstance().buildCookieJar())
            build()
        }

    }

    private val mMoshi by lazy(LazyThreadSafetyMode.NONE) {
        LibraryApplication.getInstance().buildMoshi()
    }

    private val mRetrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(MoshiConverterFactory.create(mMoshi))
                .build()
    }

    fun <T : Any> createApi(clazz: KClass<out T>) = mRetrofit.create(clazz.java)!!

}
