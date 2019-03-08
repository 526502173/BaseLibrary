package com.lz.baselibrary

import com.heyooo.heymail.utils.initializer.Initializer
import com.lz.baselibrary.utils.initializer.LibraryApiInitialize

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

    override fun onCreate() {
        Initializer.init(listOf(LibraryApiInitialize()))
        super.onCreate()
    }

//    override fun buildInterceptor(): List<Interceptor> {
//        Api.BASE_URL = ""
//        return listOf(
//                CustomInterceptor()
//        )
//    }

}
