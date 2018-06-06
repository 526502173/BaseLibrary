package com.lz.baselibrary

import com.lz.baselibrary.api.interceptor.CustomInterceptor
import okhttp3.Interceptor

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/27
 * desc   :
 * version: 1.0
</pre> *
 */
class MyApplication : LibraryApplication() {

    override fun buildInterceptor(): List<Interceptor> {
        return listOf(
                CustomInterceptor()
        )
    }

    companion object {
        fun getInstance() = LibraryApplication.getInstance()
    }

}
