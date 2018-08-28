package com.lz.baselibrary

import android.app.Application
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * @author linzheng
 */
open class LibraryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {

        private lateinit var mInstance: LibraryApplication

        fun getInstance() = mInstance
    }


    /**
     * 构建 OkHttp 的 Interceptor
     */
    open fun buildInterceptor(): List<Interceptor> = listOf()

    /**
     * 构建 OkHttp 的 NetworkInterceptor
     */
    open fun buildNetworkInterceptor(): List<Interceptor> = listOf()

    /**
     * 构建 OkHttp 的 CookieJar 对象
     */
    open fun buildCookieJar(): CookieJar = CookieJar.NO_COOKIES

    /**
     * 构建 Moshi 对象
     */
    open fun buildMoshi(): Moshi = Moshi.Builder().build()

    /**
     * 㢟 OkHttp 的 HTTPS
     */
    open fun buildOkHttpSSLSocketFactory(okHttpClientBuilder: OkHttpClient.Builder) {}

    /**
     * 构建 OKHttp 的 Cache
     */
    open fun buildCache(): Cache = Cache(cacheDir, 10 * 1024)

}
