package com.lz.baselibrary.network

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : Api
 * version: 1.0
</pre> *
 */


object Api {

    const val BASE_URL = ""

    private val mOkHttpClient by lazy(LazyThreadSafetyMode.NONE) {
        OkHttpClient.Builder()
                .build()
    }

    private val mGson by lazy(LazyThreadSafetyMode.NONE) {
        GsonBuilder()
                .create()
    }

    private val mRetrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build()
    }

    fun <T> createApi(clazz: Class<T>) = mRetrofit.create(clazz)

}
