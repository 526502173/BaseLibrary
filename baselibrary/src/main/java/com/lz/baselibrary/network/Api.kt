package com.lz.baselibrary.network

import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass

/**
 * @author linzheng
 */
object Api {

    var baseUrl = ""

    val mOkHttpClient by lazy(LazyThreadSafetyMode.NONE) {
        OkHttpClient.Builder()
                .build()
    }

    val mMoshi by lazy(LazyThreadSafetyMode.NONE) {
        Moshi.Builder()
                .build()
    }

    private val mRetrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(MoshiConverterFactory.create(mMoshi))
                .build()
    }

    fun <T : Any> createApi(clazz: KClass<out T>) = mRetrofit.create(clazz.java)

}
