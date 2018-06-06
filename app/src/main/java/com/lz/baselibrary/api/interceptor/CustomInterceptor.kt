package com.lz.baselibrary.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author linzheng
 */
class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
