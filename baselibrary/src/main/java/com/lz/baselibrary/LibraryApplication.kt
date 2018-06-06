package com.lz.baselibrary

import android.app.Application
import okhttp3.Cache
import okhttp3.Interceptor

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
     * 构建 OKHttp 的 Cache
     */
    open fun buildCache(): Cache = Cache(cacheDir, 10 * 1024)

}
