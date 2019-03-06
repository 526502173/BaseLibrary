package com.lz.baselibrary

import android.app.Application
import android.util.Log

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/27
 * desc   :
 * version: 1.0
</pre> *
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "onCreate()")
    }

//    override fun buildInterceptor(): List<Interceptor> {
//        Api.BASE_URL = ""
//        return listOf(
//                CustomInterceptor()
//        )
//    }

    companion object {
        fun getInstance() = LibraryApplication.getInstance()
    }

}
